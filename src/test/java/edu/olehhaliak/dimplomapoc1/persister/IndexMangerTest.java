package edu.olehhaliak.dimplomapoc1.persister;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class IndexMangerTest {
IndexManger manger = new IndexManger();

    @Test
    void test() {
       manger.append(25,"025.log", 0);

    }
}