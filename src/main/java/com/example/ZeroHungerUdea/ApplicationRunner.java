package com.example.ZeroHungerUdea;
import com.example.ZeroHungerUdea.model.HouseHoldIncome;
import com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics;
import com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics.*;
import com.example.ZeroHungerUdea.service.HouseHoldIncomeService;
import java.io.IOException;
import java.util.List;

import static com.example.ZeroHungerUdea.service.HouseHoldIncomeStatistics.centralTendencyMeasures;

public class ApplicationRunner {

    private static List<HouseHoldIncome> incomeList;

    public static void main(String[] args) throws IOException {

       // HouseHoldIncomeRepository incomeRepository = new HouseHoldIncomeFileRepositoryImpl ();
        //List<HouseHoldIncome> incomeList = incomeRepository.findAllHouseHoldIncome();
        centralTendencyMeasures ();
        HouseHoldIncomeStatistics.statistics(incomeList);
        HouseHoldIncomeStatistics.generateReportPDF (incomeList);
        HouseHoldIncomeStatistics.generateStatisticsCharts (incomeList);

    }
}


