package ru.rob.model.debt.service;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest extends QueryServiceImpl {

    Date requestStartDate, requestEndDate;

    List<Map<String, Object>> collect = new ArrayList<>();

    PeriodTest() {
        super(null);
    }

    //    @Test
    void testTest0() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Comparator<Integer> comparator
                = (i1, i2) -> i2 - i1;
        List<Integer> collect = list
                .stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedList::new));
        assertEquals(2, collect.get(0));
        assertEquals(1, collect.get(1));
        System.out.println();
    }

    @Test
    void listEmplyTest() {
        assertTrue(haveDebtByFlsAttr(null, null, null));
        assertTrue(haveDebtByFlsAttr(new ArrayList<>(), null, null));
        System.out.println();
    }

    List<Map<String, Object>> maps = new ArrayList<>();

//    @BeforeClass void init()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("startAttr", Timestamp.valueOf("2022-05-01 15:28:41.349"));
        map.put("finishAttr", Timestamp.valueOf("5000-01-01 00:00:00.000"));
        map.put("valueAttr", 0);
        maps.add(map);
        map = new HashMap<>();
        map.put("startAttr", Timestamp.valueOf("2022-05-01 15:25:44.281"));
        map.put("finishAttr", Timestamp.valueOf("2022-05-01 15:28:41.348"));
        map.put("valueAttr", 1);
        maps.add(map);
    }

    @Test
    void listTest2() {
        requestStartDate = Date.from(LocalDateTime.parse("2022-05-01T15:25:44.279")
                .atZone(ZoneId.systemDefault()).toInstant());
        requestEndDate = Date.from(LocalDateTime.parse("2022-05-01T15:25:44.280")
                .atZone(ZoneId.systemDefault()).toInstant());
        assertTrue(haveDebtByFlsAttr(maps, requestStartDate, requestEndDate));
    }

    @Test
    void listTest1() {
        requestStartDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.346")
                .atZone(ZoneId.systemDefault()).toInstant());
        requestEndDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.347")
                .atZone(ZoneId.systemDefault()).toInstant());
        assertTrue(haveDebtByFlsAttr(maps, requestStartDate, requestEndDate));
    }

    @Test
    void listTest0() {
        requestStartDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.350")
                .atZone(ZoneId.systemDefault()).toInstant());
        requestEndDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.351")
                .atZone(ZoneId.systemDefault()).toInstant());
        assertFalse(haveDebtByFlsAttr(maps, requestStartDate, requestEndDate));
    }

    @Test
    void listTest() {
        requestStartDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.347")
                .atZone(ZoneId.systemDefault()).toInstant());
        requestEndDate = Date.from(LocalDateTime.parse("2022-05-01T15:28:41.350")
                .atZone(ZoneId.systemDefault()).toInstant());
        assertFalse(haveDebtByFlsAttr(maps, requestStartDate, requestEndDate));
    }

}