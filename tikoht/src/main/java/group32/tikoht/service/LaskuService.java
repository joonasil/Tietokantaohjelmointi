package group32.tikoht.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TyosopimusDao;
import group32.tikoht.dao.LaskuDao;
import group32.tikoht.model.Lasku;

import static java.time.temporal.ChronoUnit.DAYS;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class LaskuService {

    private final LaskuDao laskuDao;
    private final TyosopimusDao sopimusDao;

    @Autowired
    public LaskuService(@Qualifier("laskuPSQL") LaskuDao laskuDao, @Qualifier("tyosopimusPSQL") TyosopimusDao sopimusDao) {
        this.laskuDao = laskuDao;
        this.sopimusDao = sopimusDao;
    }

    public int addLasku(Lasku lasku) {
        return laskuDao.insert(lasku);
    }

    public List<Lasku> getAllLasku() {
        return laskuDao.selectAll();
    }

    public Optional<Lasku> getLaskuById(Integer laskuID) {
        return laskuDao.selectById(laskuID);
    }

    public int deleteLasku(Integer laskuID) {
        return laskuDao.deleteById(laskuID);
    }

    public int updateLasku(Integer laskuID, Lasku lasku) {
        return laskuDao.updateById(laskuID, lasku);
    }

    // Laskun omat
    public List<Lasku> getAllOverdue() { return laskuDao.selectAllOverdue();}

    public int generateOverdueInvoices() {
        List<Lasku> overdueInveoices = laskuDao.selectAllOverdue();
        List<Integer> unremaindedInvoices = new ArrayList<Integer>();

        for (Lasku formerInvoice : overdueInveoices) {
           unremaindedInvoices.add(formerInvoice.getLaskuID());
           if (unremaindedInvoices.contains(formerInvoice.getEdeltavaLasku())) {
               unremaindedInvoices.remove(formerInvoice.getEdeltavaLasku());
           }
        }
        System.out.println(unremaindedInvoices);

        LocalDate localDate = java.time.LocalDate.now();
        int inserted = 0;
        for (Lasku formerInvoice : overdueInveoices) {
            if (unremaindedInvoices.contains(formerInvoice.getLaskuID())) {

                Integer laskuID = null;
                Integer sopimusID = formerInvoice.getSopimusID();
                LocalDate pvm = localDate;
                LocalDate erapaiva = localDate.plusDays(30);
                LocalDate maksettuPvm = null;
                Integer edeltavaLasku = formerInvoice.getLaskuID();
                Integer muistutusLkm = formerInvoice.getMuistutusLkm() == null ? 1 : formerInvoice.getMuistutusLkm() + 1;
                Double viivastyskulut, viivastyskorko;
                if (muistutusLkm >= 2) {
                    Double summa = sopimusDao.urakkaSumma(formerInvoice.getSopimusID());
                    System.out.println("edellinen: " + formerInvoice.getSopimusID() + " : summa " + summa);
                    viivastyskorko = (0.16 * (DAYS.between(localDate, formerInvoice.getErapaiva()) / 365) * summa);
                    viivastyskulut = formerInvoice.getViivastyskulut() + 5 + viivastyskorko;
                }
                else {
                    viivastyskulut = formerInvoice.getViivastyskulut() + 5;
                }

                Lasku reminder = new Lasku(laskuID, sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut);
                if (laskuDao.insert(reminder) == 1) {
                    inserted++;
                }
            }
        }
        return inserted;
    }

}