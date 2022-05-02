package ru.rob.model.debt.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rob.model.debt.repository.DebtRequestRepository;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl implements QueryService {

    private final Logger LOGGER = LoggerFactory.getLogger(QueryServiceImpl.class);

    @Autowired
    private DebtRequestRepository repository;

    private SessionFactory sessionFactory;

    @Autowired
    public QueryServiceImpl(EntityManagerFactory factory) {
        if(factory == null){
//            throw new NullPointerException("factory is not a hibernate factory");
        }  else {
            this.sessionFactory = factory.unwrap(SessionFactory.class);
        }
    }

    @Override
    @Transactional
    public void tryRequest() {
        LOGGER.info(" -> EXEC SQL");
//        List<DebtRequest> all = repository.findAll();

//        Session session = null;
        List<Map<String, Object>> list = null;
        try (Session session = sessionFactory.openSession()) {
            final String ATTR_SQL = new StringBuilder(
                    "  SELECT fp.svd startAttr, fp.fvd finishAttr, fp.val_num valueAttr" +
                            "    FROM fias_house fh JOIN house h ON fh.fiasid = h.fias_house " +
                            "         JOIN house_addr ha ON h.addr = ha.addr " +
                            "         JOIN fls f ON h.addr = f.house " +
                            "         JOIN fls_flat ff ON f.id = ff.fls " +
                            "         JOIN flat fl ON ff.flat = fl.addr ")
                    .append(new Object() != null ?
                            "         AND fl.nomer = :flatNumber " : "")
                    .append("         JOIN fls_param fp ON f.id = fp.fls " +
                            "   WHERE fh.houseguid = :fiasGuid AND fp.attr = 2063 " +
                            "ORDER BY DESC " )
                    .toString();
            final Query query = session.createSQLQuery(ATTR_SQL)
                    .addScalar("startAttr", StandardBasicTypes.TIMESTAMP)
                    .addScalar("finishAttr", StandardBasicTypes.TIMESTAMP)
                    .addScalar("valueAttr", StandardBasicTypes.INTEGER)
                    .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
                    .setString("fiasGuid", "b3e00ba3-a857-4549-b0af-cb48f8ddb974");
            if ("1" != null) {
                query.setString("flatNumber", "1");
            }
            list = query.list();
        }

        Date requestStartDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.346")
                .atZone(ZoneId.systemDefault()).toInstant());
        Date requestEndDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.347")
                .atZone(ZoneId.systemDefault()).toInstant());
        boolean anyFalse =
                ((Timestamp) list.get(0).get("finishAttr"))
                        .compareTo(requestStartDate)>=0;
list.remove(0);
//        boolean hasDebt =
//        Map<String, Object> stringObjectMap =
        List<Map<String, Object>> collect =
//        Optional<Map<String, Object>> max =
                list.stream()
                .filter(map -> ((Timestamp) map.get("startAttr")).compareTo(requestEndDate) <= 0)
                .filter(map -> ((Timestamp) map.get("finishAttr")).compareTo(requestStartDate) >= 1)
//                .max(Comparator.comparing(map -> ((Timestamp) map.get("finishAttr"))));
//                .map(map -> ((Integer)map.get("valueAttr")) == 1)
//                .get()
                .collect(Collectors.toList());


        LOGGER.info(" -> EXEC SQL complete: ");
        System.out.println();
    }

    boolean haveDebtByFlsAttr(List<Map<String, Object>> maps, Date requestStartDate, Date requestEndDate) {
//        Comparator<Timestamp> endDateRequestBetween = null;
//        Comparator<Map<String, Object>> comparator
//                = (m1, m2) -> m1.getName().compareTo(h2.getName())
        if (maps == null) {
            return true;
        } else {
            for (Map<String, Object> map : maps) {
                if (((Timestamp) map.get("startAttr")).compareTo(requestEndDate) <= 0) {
                    if ( ((Integer)map.get("valueAttr")) == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            return true;
        }
//                maps.stream().sorted(Comparator.comparing(map -> ((Timestamp) map.get("finishAttr"))));
//                maps.forEach(map -> map.get("finishAttr"));
//                List<Map<String, Object>> toSort = new ArrayList<>();
//                for (Map<String, Object> stringObjectMap : maps) {
//                    (Timestamp) stringObjectMap.get("finishAttr");
//                }
//                toSort.sort(Comparator.reverseOrder());

//                for (Map<String, Object> map : maps) {
//                    endDateRequestBetween = (startAttr, finishAttr) -> startAttr.compareTo((Timestamp)map.get(finishAttr)<=0)
//                }

//                maps.removeIf(map -> ((Timestamp) map.get("startAttr")).after(requestEndDate));
//                maps.removeIf(map -> ((Timestamp) map.get("finishAttr")).before(requestStartDate));
//                maps.b

            /*if (maps.size()>1) {
                maps.stream()

                        .filter(map -> ((Timestamp) map.get("startAttr")).compareTo(requestEndDate) <= 0)
                        .filter(map -> ((Timestamp) map.get("finishAttr")).compareTo(requestStartDate) >= 1)
                        .max(Comparator.comparing(map -> ((Timestamp) map.get("finishAttr"))));

            }*/
    }

}
