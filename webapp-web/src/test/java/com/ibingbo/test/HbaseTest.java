package com.ibingbo.test;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/**
 * Created by bing on 2017/1/16.
 */
public class HbaseTest {

    @Test
    public void testHbase(){
        Configuration configuration = HBaseConfiguration.create();
        //configuration.set("hbase.master", "nnode:60000");
        configuration.set("hbase.zookeeper.property.clientPort","2181");
        configuration.set("hbase.zookeeper.quorum", "hbasemaster153.et2.tbsite.net,hbasemaster154.et2.tbsite.net,hbasemaster155.et2.tbsite.net,hbase4866.et2.tbsite.net,hbase4867.et2.tbsite.net");

        try {
            HConnection connection = HConnectionManager.createConnection(configuration);
            String[] tables = connection.getTableNames();
            HTableInterface tableInterface = connection.getTable("aaa");
            HTablePool pool = new HTablePool(configuration, 30);
            HTableInterface table = pool.getTable("TABLE_NAME");
            Scan scan = new Scan();
            scan.setStartRow(Bytes.toBytes("aaa"));
            scan.setStopRow(Bytes.toBytes("bbb"));
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                System.out.println(result);
            }
            resultScanner.close();
            table.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
