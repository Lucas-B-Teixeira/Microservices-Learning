package mslearning.hrpayroll.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerDTO {
    private String name;
    private Double dailyIncome;
}