package cn.leixin;

import cn.leixin.dao.Shares;
import cn.leixin.dao.SharesDao;
import com.csvreader.CsvReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class ReadCSVTest {
    @Autowired
    SharesDao sharesDao;
    @Test
    public void test(){
        try {
            String[] ls = null;
            CsvReader reader = new CsvReader("D:/2018-03-05.csv", ' ', Charset.forName("UTF-8"));
            // 跳过表头
            reader.readHeaders();
            // 开始读取数据
            while (reader.readRecord()) {
                ls=reader.getValues();
                ArrayList<String> csvFileList = new ArrayList();
                for(String str : ls) {
                    if (null != str && !str.equals("")){
                        csvFileList.add(str);
                    }
                }
                for (int i =1;i<csvFileList.size();i++) {
                        Shares shares = new Shares();
                        shares.setId(uuid.getUUID());
                        shares.setStockCode(csvFileList.get(0));
                        shares.setTradingDate(new Date());
                        shares.setItemValue(Double.valueOf(csvFileList.get(i)));
                        sharesDao.insert(shares);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
