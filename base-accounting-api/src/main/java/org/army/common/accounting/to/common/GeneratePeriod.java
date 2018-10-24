package org.army.common.accounting.to.common;

public abstract class GeneratePeriod {

    public interface GenerateType {
        String ANNUAL = "ANNUAL";
        String QUARTER = "QUARTER";
        String CUSTOM = "CUSTOM";
    }

    private String generatePeriod;

    public String getGeneratePeriod() {
        return generatePeriod;
    }

    public void setGeneratePeriod(String generatePeriod) {
        this.generatePeriod = generatePeriod;
    }
}
