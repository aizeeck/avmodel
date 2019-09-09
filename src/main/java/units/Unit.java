package units;

import utils.StringConvertor;

import java.util.Map;

public class Unit {
    private int unitId;
    private String enterprise;
    private String unitType;
    private double revenues;
    private double expenses;
    private double taxes;
    private double profitability;
    private double profit;
    private double emplSalary;
    private double emplBaseSalary;
    private int emplQnt;
    private int emplQntMax;
    private double emplLevel;
    private double emplLevelReq;
    private double emplEff;
    private int equiQnt;
    private int equiQntMax;
    private double equilLevel;
    private double equilLevelReq;
    private double equiWairPercent;
    private int equiWairNum;
    private double eqiuEff;
    private boolean hasEmployee;
    private boolean hasEquipment;

    public Unit(int unitId, Map<String, String> unitsGeneralProperties) {
        setAttributes(unitId, unitsGeneralProperties);
    }

    private void setAttributes(int unitId, Map<String, String> unitsGeneralProperties) {
        setUnitId(unitId);
        setEnterprise(unitsGeneralProperties.get("Enterprise"));
        setUnitType(unitsGeneralProperties.get("unitType"));
        setRevenues(StringConvertor.convertToDouble(unitsGeneralProperties.get("Revenues")));
        setExpenses(StringConvertor.convertToDouble(unitsGeneralProperties.get("Expenses")));
        setTaxes(StringConvertor.convertToDouble(unitsGeneralProperties.get("Taxes")));
        setProfit(StringConvertor.convertToDouble(unitsGeneralProperties.get("Profit")));
        setProfitability(StringConvertor.convertToDouble(unitsGeneralProperties.get("Profitability")));

        setEmplBaseSalary(StringConvertor.convertToDouble(unitsGeneralProperties.get("emplBaseSalary")));
        setEmplSalary(StringConvertor.convertToDouble(unitsGeneralProperties.get("emplSalary")));
        setEmplQnt(StringConvertor.convertToInt(unitsGeneralProperties.get("emplQnt")));
        setEmplQntMax(StringConvertor.convertToInt(unitsGeneralProperties.get("emplQntMax")));
        setEmplLevel(StringConvertor.convertToDouble(unitsGeneralProperties.get("emplLevel")));
        setEmplLevelReq(StringConvertor.convertToDouble(unitsGeneralProperties.get("emplLevelReq")));
        setEmplEff(StringConvertor.convertToDouble(unitsGeneralProperties.get("emplEff")));
        
        setEquiQnt(StringConvertor.convertToInt(unitsGeneralProperties.get("equiQnt")));
        setEquiQntMax(StringConvertor.convertToInt(unitsGeneralProperties.get("equiQntMax")));
        setEquilLevel(StringConvertor.convertToDouble(unitsGeneralProperties.get("equilLevel")));
        setEquilLevelReq(StringConvertor.convertToDouble(unitsGeneralProperties.get("equilLevelReq")));
        setEquiWairPercent(StringConvertor.convertToDouble(unitsGeneralProperties.get("equiWairPercent")));
        setEquiWairNum(StringConvertor.convertToInt(unitsGeneralProperties.get("equiWairNum")));
        setEqiuEff(StringConvertor.convertToDouble(unitsGeneralProperties.get("eqiuEff")));
        setEquilLevelReq(StringConvertor.convertToDouble(unitsGeneralProperties.get("equilLevelReq")));

        setHasEmployee(unitsGeneralProperties.get("hasEmployee"));
        setHasEquipment(unitsGeneralProperties.get("hasEquipment"));

    }

    public int getUnitId() {
        return unitId;
    }

    private void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getEnterprise() {
        return enterprise;
    }

    private void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getUnitType() {
        return unitType;
    }

    private void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public double getRevenues() {
        return revenues;
    }

    private void setRevenues(double revenues) {
        this.revenues = revenues;
    }

    public double getExpenses() {
        return expenses;
    }

    private void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getTaxes() {
        return taxes;
    }

    private void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getProfitability() {
        return profitability;
    }

    private void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public double getProfit() {
        return profit;
    }

    private void setProfit(double profit) {
        this.profit = profit;
    }


    public double getEmplSalary() {
        return emplSalary;
    }

    private void setEmplSalary(double emplSalary) {
        this.emplSalary = emplSalary;
    }

    public double getEmplBaseSalary() {
        return emplBaseSalary;
    }

    private void setEmplBaseSalary(double emplBaseSalary) {
        this.emplBaseSalary = emplBaseSalary;
    }

    public int getEmplQnt() {
        return emplQnt;
    }

    private void setEmplQnt(int emplQnt) {
        this.emplQnt = emplQnt;
    }

    public int getEmplQntMax() {
        return emplQntMax;
    }

    private void setEmplQntMax(int emplQntMax) {
        this.emplQntMax = emplQntMax;
    }

    public double getEmplLevel() {
        return emplLevel;
    }

    private void setEmplLevel(double emplLevel) {
        this.emplLevel = emplLevel;
    }

    public double getEmplLevelReq() {
        return emplLevelReq;
    }

    private void setEmplLevelReq(double emplLevelReq) {
        this.emplLevelReq = emplLevelReq;
    }

    public double getEquiWairPercent() {
        return equiWairPercent;
    }

    private void setEquiWairPercent(double equiWairPercent) {
        this.equiWairPercent = equiWairPercent;
    }

    public int getEquiWairNum() {
        return equiWairNum;
    }

    private void setEquiWairNum(int equiWairNum) {
        this.equiWairNum = equiWairNum;
    }

    public double getEqiuEff() {
        return eqiuEff;
    }

    private void setEqiuEff(double eqiuEff) {
        this.eqiuEff = eqiuEff;
    }

    public double getEmplEff() {
        return emplEff;
    }

    private void setEmplEff(double emplEff) {
        this.emplEff = emplEff;
    }

    public int getEquiQnt() {
        return equiQnt;
    }

    private void setEquiQnt(int equiQnt) {
        this.equiQnt = equiQnt;
    }

    public int getEquiQntMax() {
        return equiQntMax;
    }

    private void setEquiQntMax(int equiQntMax) {
        this.equiQntMax = equiQntMax;
    }

    public double getEquilLevel() {
        return equilLevel;
    }

    private void setEquilLevel(double equilLevel) {
        this.equilLevel = equilLevel;
    }

    public double getEquilLevelReq() {
        return equilLevelReq;
    }

    private void setEquilLevelReq(double equilLevelReq) {
        this.equilLevelReq = equilLevelReq;
    }

    public boolean getHasEmployee() {
        return hasEmployee;
    }

    private void setHasEmployee(String hasEmployee) {
        if (hasEmployee == null) {
            this.hasEmployee = false;
        } else if (hasEmployee.equals("true")){
            this.hasEmployee = true;
        } else {
            this.hasEmployee = false;
        }
    }

    public boolean getHasEquipment() {
        return hasEquipment;
    }

    private void setHasEquipment(String hasEquipment) {
        if (hasEquipment == null) {
            this.hasEquipment = false;
        } else if (hasEquipment.equals("true")){
            this.hasEquipment = true;
        } else {
            this.hasEquipment = false;
        }
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitId=" + unitId +
                ", enterprise='" + enterprise + '\'' +
                ", unitType='" + unitType + '\'' +
                ", revenues=" + revenues +
                ", expenses=" + expenses +
                ", taxes=" + taxes +
                ", profitability=" + profitability +
                ", profit=" + profit +
                ", emplBaseSalary=" + emplBaseSalary +
                ", emplQnt=" + emplQnt +
                ", emplQntMax=" + emplQntMax +
                ", emplLevel=" + emplLevel +
                ", emplLevelReq=" + emplLevelReq +
                ", emplEff=" + emplEff +
                ", equiQnt=" + equiQnt +
                ", equiQntMax=" + equiQntMax +
                ", equilLevel=" + equilLevel +
                ", equilLevelReq=" + equilLevelReq +
                ", equiWairPercent=" + equiWairPercent +
                ", equiWairNum=" + equiWairNum +
                ", eqiuEff=" + eqiuEff +
                ", hasEmployee=" + hasEmployee+
                ", hasEquipment=" + hasEquipment +
                '}' +
                "\n";
    }
}
