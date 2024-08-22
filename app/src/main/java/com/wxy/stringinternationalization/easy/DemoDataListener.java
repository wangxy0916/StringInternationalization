//package com.wxy.stringinternationalization.easy;
//
//import android.util.Log;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.read.listener.ReadListener;
//
//public
//class DemoDataListener implements ReadListener {
//    @Override
//    public void invoke(Object data, AnalysisContext context) {
//// 处理每一行的数据
//        LanguageBean bean = (LanguageBean) data;
//        Log.d("wxyy", "id = " + bean.getId() + ", english = " + bean.getEnglish());
//
//    }
//
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//// 数据读取完成
//    }
//}
