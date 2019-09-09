package units.departments;

import browser.Browser;
import config.Configuration;
import units.EmployeeUnit;
import utils.StringConvertor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRDepartment {

    private static final String EMPL_CALC_LVL = Configuration.getInstance().getProperty("EMPL_CALC_LVL");
    private static final String EMPL_ENGAGE = Configuration.getInstance().getProperty("EMPL_ENGAGE");
    private Browser browser = new Browser();

    public void setOptimalSalary(List<EmployeeUnit> units) {
        for (EmployeeUnit unit : units) {
            setOptimalSalary(unit);
        }
    }

    public void setOptimalSalary(EmployeeUnit unit) {

        double STEP = unit.getEmplSalary() * 0.1;
        int exitCountdown = 1;
        double currentQualification = unit.getEmplLevel();
        double requiredQualification = unit.getEmplLevelReq();
        double salary = unit.getEmplSalary();

        if (currentQualification == requiredQualification || unit.getEmplQnt() == 0) return;

        while (currentQualification != requiredQualification && exitCountdown <= 30) {
            boolean next = false;
            while (currentQualification < requiredQualification) {
                next = true;
                salary = salary + STEP;
                String salaryHtml = browser.getPage(EMPL_CALC_LVL + unit.getUnitId() + "?employees=" + unit.getEmplQnt() + "&salary=" + salary).toString();
                if (salaryHtml.contains("{\"employees_level\":\"0\",\"msg\":\"This salary is too low even for slaves!\"}")) {
                    continue;
                } else {
                    currentQualification = StringConvertor.convertToDouble(salaryHtml);
                }
            }

            if (next) {
                continue;
            }

            while (currentQualification > requiredQualification) {
                salary = salary - STEP;
                String salaryHtml = browser.getPage(EMPL_CALC_LVL + unit.getUnitId() + "?employees=" + + unit.getEmplQnt() + "&salary=" + salary).toString();
                if (salaryHtml.contains("{\"employees_level\":\"0\",\"msg\":\"This salary is too low even for slaves!\"}")) {
                    currentQualification = 0;
                    continue;
                } else {
                    currentQualification = StringConvertor.convertToDouble(salaryHtml);
                }
            }
            exitCountdown++;
            STEP = STEP / exitCountdown;
        }
        postSalary(unit, salary);
    }

    private void postSalary(EmployeeUnit unit, double salary) {
        Map<String, String> postParams = new HashMap<>();
        postParams.put("unitEmployeesData[quantity]", String.valueOf(unit.getEmplQnt()));
        postParams.put("unitEmployeesData[salary]", String.valueOf(salary));
        System.out.println(unit.getUnitId() + " " + postParams);
        try {
            browser.sendPost(
                    EMPL_ENGAGE + unit.getUnitId(),
                    EMPL_ENGAGE + unit.getUnitId(),
                    postParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
