package com.bny.util;

import com.bny.pages.vendorportal.model.VendorPortalTestData;

public class JsonReaderDemo
{
    public static void main(String[] args) {
        VendorPortalTestData testData = JsonUtil.getTestData("test-data/vendor-portal/john.json");
        System.out.println(testData.monthlyEarning());
    }
}
