package org.lani;

import org.lani.enumeration.TypeEnum;
import org.lani.exception.NoSuchTypeException;
/**
 * CalculateAnnualSalart클래스는 Employee의 인상된 연봉을 계산할 때 쓴다.
 */
public class CalculateAnnualSalary {
    /**
     * employee에 따른 인상된 연봉을 계산한다.
     * @param employee Employee 인스턴스
     * @return 인상된 연봉(세금 미포함)
     */
    public double getIncresedAnnualSalary(Employee employee) throws NoSuchTypeException {
        switch (TypeEnum.getEnum(employee.getType())) {
            case OWNER:
                return employee.getSalary() * 0.7;  //owner일 경우
        
            case MANAGER:
                return employee.getSalary() * 1.1;  //manager일 경우
            
            case DESIGNER:
                return employee.getSalary() * 1.2;  //designer일 경우

            case ARCHITECT:
                return employee.getSalary() * 1.3; //architect일 경우

            case DEVELOPER:
                return employee.getSalary() * 1.6;  //developer일 경우

            default:
                throw new NoSuchTypeException();
        }
    }
}