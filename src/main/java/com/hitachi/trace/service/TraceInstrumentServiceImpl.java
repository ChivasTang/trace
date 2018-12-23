package com.hitachi.trace.service;

import com.hitachi.trace.dao.TraceInstrumentDao;
import com.hitachi.trace.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TraceInstrumentServiceImpl implements TraceInstrumentService {

    @Resource
    private TraceInstrumentDao traceInstrumentDao;

    @Override
    public List<TraceInstrumentGraphResDomain> selectGraphList() {
        List<TraceInstrumentGraphDomain> graphList = traceInstrumentDao.findAll();
        return getGraphResList(graphList);
    }

    private static List<TraceInstrumentGraphResDomain> getGraphResList(List<TraceInstrumentGraphDomain> graphList){
        List<TraceInstrumentGraphResDomain> graphResList=new ArrayList<>();
        List<Long> instDispIdList=new ArrayList<>();
        TraceInstrumentGraphResDomain graphResDomain=new TraceInstrumentGraphResDomain();

        graphResDomain.setInst_disp_id((long)0);
        graphResDomain.setInst_disp_series(getGraphSeriesList(graphList,graphResDomain));
        graphResList.add(graphResDomain);

        for (TraceInstrumentGraphDomain graph : graphList) {
            if (instDispIdList.indexOf(graph.getInst_disp_id()) < 0) {
                instDispIdList.add(graph.getInst_disp_id());
                TraceInstrumentGraphResDomain graphRes=new TraceInstrumentGraphResDomain();
                graphRes.setInst_disp_id(graph.getInst_disp_id());
                graphRes.setInst_disp_name(graph.getInst_disp_name());
                System.out.println(graphRes);
                graphRes.setInst_disp_series(getGraphSeriesList(graphList,graphRes));
                graphResList.add(graphRes);
            }
        }

        return graphResList;
    }

    private static List<TraceInstrumentGraphSeriesDomain> getGraphSeriesList(List<TraceInstrumentGraphDomain> graphList, TraceInstrumentGraphResDomain graphRes) {
        List<TraceInstrumentGraphSeriesDomain> graphSeriesList = new ArrayList<>();
        List<Integer> appCodeList = new ArrayList<>();
        List<Integer> sumAppCodeList = new ArrayList<>();
        for (TraceInstrumentGraphDomain graph : graphList) {

            if(graphRes.getInst_disp_id()==(long) 0 && sumAppCodeList.indexOf(graph.getApplication_code())<0){
                sumAppCodeList.add(graph.getApplication_code());
                TraceInstrumentGraphSeriesDomain graphSeries = new TraceInstrumentGraphSeriesDomain();
                graphSeries.setInst_disp_id((long)0);
                graphSeries.setApplication_code(graph.getApplication_code());
                graphSeries.setName(graph.getApplication_name());
                graphSeries.setMarkers(getGraphSeriesMarkersList(graph.getApplication_code()));
                graphSeries.setData(getGraphSeriesDataList(graphList, graphSeries));
                graphSeriesList.add(graphSeries);
            }

            if(graphRes.getInst_disp_id()==graph.getInst_disp_id() && appCodeList.indexOf(graph.getApplication_code()) < 0){
                appCodeList.add(graph.getApplication_code());
                TraceInstrumentGraphSeriesDomain graphSeries = new TraceInstrumentGraphSeriesDomain();
                graphSeries.setInst_disp_id(graphRes.getInst_disp_id());
                graphSeries.setApplication_code(graph.getApplication_code());
                graphSeries.setName(graph.getApplication_name());
                graphSeries.setMarkers(getGraphSeriesMarkersList(graphSeries.getApplication_code()));
                graphSeries.setData(getGraphSeriesDataList(graphList,graphSeries));
                System.out.println(graphSeries);
                graphSeriesList.add(graphSeries);
            }
        }
        return graphSeriesList;
    }

    private static List<TraceInstrumentGraphSeriesDataDomain> getGraphSeriesDataList(List<TraceInstrumentGraphDomain> graphList, TraceInstrumentGraphSeriesDomain graphSeries) {
        List<TraceInstrumentGraphSeriesDataDomain> graphSeriesDataList = new ArrayList<>();
        List<String> dateList = getDateList(graphList);
        for (String date : dateList) {
            TraceInstrumentGraphSeriesDataDomain graphSeriesData = new TraceInstrumentGraphSeriesDataDomain();
            graphSeriesData.setInst_disp_id(graphSeries.getInst_disp_id());
            graphSeriesData.setApplication_code(graphSeries.getApplication_code());
            graphSeriesData.setDate(date);
            graphSeriesData.setValue(0);
            System.out.println(graphSeriesData);
            graphSeriesDataList.add(graphSeriesData);
        }

        System.out.println(graphSeriesDataList);

        for (TraceInstrumentGraphDomain graph : graphList) {
            for (TraceInstrumentGraphSeriesDataDomain graphSeriesData : graphSeriesDataList) {
                if(graphSeriesData.getInst_disp_id()==(long)0 && graphSeriesData.getApplication_code()==graph.getApplication_code() && graphSeriesData.getDate()==graph.getCreate_date()){
                    int value = graphSeriesData.getValue();
                    value += graph.getSum_count();
                    graphSeriesData.setValue(value);
                }

                if (graph.getInst_disp_id() == graphSeriesData.getInst_disp_id() && graph.getApplication_code() == graphSeriesData.getApplication_code() && graph.getCreate_date().equals(graphSeriesData.getDate())) {
                    int value = graphSeriesData.getValue();
                    value += graph.getSum_count();
                    graphSeriesData.setValue(value);
                }
            }
        }

        return graphSeriesDataList;
    }

    private static List<String> getDateList(List<TraceInstrumentGraphDomain> graphList){
        List<String> dateList=new ArrayList<>();
        for (TraceInstrumentGraphDomain graph : graphList) {
            if(dateList.indexOf(graph.getCreate_date())<0){
                dateList.add(graph.getCreate_date());
            }
        }
        Collections.sort(dateList);
        String from_date=dateList.get(0);
        String to_date=dateList.get(dateList.size()-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateList=findDates(sdf.parse(from_date),sdf.parse(to_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateList;
    }

    private static List<String> findDates(Date dBegin, Date dEnd){
        List<String> lDate = new ArrayList<>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        while (dEnd.after(calBegin.getTime()))
        {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }

    private static List<TraceInstrumentGraphSeriesMarkersDomain> getGraphSeriesMarkersList(int num) {
        List<TraceInstrumentGraphSeriesMarkersDomain> graphSeriesMarkersList=new ArrayList<>();
        String[] markersTypes = {"circle", "square", "triangle", "cross"};
        TraceInstrumentGraphSeriesMarkersDomain markers=new TraceInstrumentGraphSeriesMarkersDomain();
        markers.setType(markersTypes[(num % 4)]);
        graphSeriesMarkersList.add(markers);
        return graphSeriesMarkersList;
    }

}
