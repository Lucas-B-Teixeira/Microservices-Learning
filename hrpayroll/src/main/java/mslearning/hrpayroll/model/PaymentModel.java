package mslearning.hrpayroll.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentModel {

    private String name;
    private Double dailyIncome;
    private Integer days;

    public double getTotal(){
        return days * dailyIncome;
    }

}
