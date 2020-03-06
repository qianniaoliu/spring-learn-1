package com.athena.sample.array;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yusheng
 */
public class InsertSortDemo {

    public static void main(String[] args) {
        /*int[] data = new int[]{3, 2, 5, 4, 6, 7, 45, 4354, 231};
        int n = data.length;
        for (int i = 1; i < n; i++) {
            int swapData = data[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if(data[j] > swapData){
                    data[j + 1] = data[j];
                }else {
                    break;
                }
            }
            data[j + 1] = swapData;
        }
        System.out.println(data);*/


        List<CbjResultBean> cbjResultBeans = new ArrayList<>();
        CbjResultBean c1 = new CbjResultBean();
        c1.setWid(110L);
        c1.setOrgId(623);
        c1.setPrice(BigDecimal.valueOf(1232L));
        cbjResultBeans.add(c1);
        CbjResultBean c2 = new CbjResultBean();
        c2.setWid(110L);
        c2.setOrgId(3432);
        c2.setPrice(BigDecimal.valueOf(4341L));
        cbjResultBeans.add(c2);
        CbjResultBean c3= new CbjResultBean();
        c3.setWid(110L);
        c3.setOrgId(31245);
        c3.setPrice(BigDecimal.valueOf(453L));
        cbjResultBeans.add(c3);
        CbjResultBean c4 = new CbjResultBean();
        c4.setWid(110L);
        c4.setOrgId(707);
        c4.setPrice(BigDecimal.valueOf(4123L));
        cbjResultBeans.add(c4);
        CbjResultBean c5 = new CbjResultBean();
        c5.setWid(432L);
        c5.setOrgId(10);
        c5.setPrice(BigDecimal.valueOf(545431L));
        cbjResultBeans.add(c5);
        CbjResultBean c6 = new CbjResultBean();
        c6.setWid(432L);
        c6.setOrgId(23432);
        c6.setPrice(BigDecimal.valueOf(123523L));
        cbjResultBeans.add(c6);
        insertionSort(cbjResultBeans, cbjResultBeans.size());
        Map<Long, List<CbjResultBean>> cbjBySkuIdMap = new HashMap<>();
        for(CbjResultBean cbjResultBean : cbjResultBeans){
            cbjBySkuIdMap.compute(cbjResultBean.getWid(), (key, val)->{
                if(val == null){
                    val = new ArrayList<>();
                }
                val.add(cbjResultBean);
                return val;
            });
        }
        System.out.println(111);

    }

    private static void insertionSort(List<CbjResultBean> cbjResultBeans, int n){
        if(n <= 1){
            return;
        }
        //广州
        int gzOrg = 10;
        //东莞
        int dgOrg = 707;
        //广州图书
        int gzBookOrg = 623;
        for(int i = 1; i < n; i++){
            CbjResultBean value = cbjResultBeans.get(i);
            int j = i - 1;
            for(; j >=0; j--){
                CbjResultBean c = cbjResultBeans.get(j);
                if(value.getOrgId() == gzOrg){
                    cbjResultBeans.set(j + 1, c);
                }else if(value.getOrgId() == dgOrg && c.getOrgId() != gzOrg){
                    cbjResultBeans.set(j + 1, c);
                }else if(value.getOrgId() == gzBookOrg && c.getOrgId() != gzOrg && c.getOrgId() != dgOrg){
                    cbjResultBeans.set(j + 1, c);
                }else {
                    if(c.getOrgId() != gzOrg && c.getOrgId() != dgOrg && c.getOrgId() != gzBookOrg){
                        if(value.getPrice().compareTo(c.getPrice()) == 1){
                            cbjResultBeans.set(j + 1, c);
                        }else {
                            break;
                        }
                    }else {
                        break;
                    }
                }
            }
            cbjResultBeans.set(j + 1, value);
        }
    }

    static class CbjResultBean{
        private Integer orgId;
        private Long wid;
        private Integer quantity;
        private BigDecimal price;

        public Integer getOrgId() {
            return orgId;
        }

        public void setOrgId(Integer orgId) {
            this.orgId = orgId;
        }

        public Long getWid() {
            return wid;
        }

        public void setWid(Long wid) {
            this.wid = wid;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
