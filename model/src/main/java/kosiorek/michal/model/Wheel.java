package kosiorek.michal.model;


import kosiorek.michal.model.enums.TireType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wheel {

    private String model;
    private int size;
    private TireType tireType;

}
