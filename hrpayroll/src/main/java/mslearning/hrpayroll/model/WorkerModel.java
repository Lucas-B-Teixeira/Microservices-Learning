package mslearning.hrpayroll.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerModel {

    private Long id;

    private String name;

    private Double dailyIncome;

}
