package kosiorek.michal.model;

import kosiorek.michal.model.enums.CarBodyType;
import kosiorek.michal.model.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarBody {

    private Color color;
    private CarBodyType carBodyType;
    private List<String> components;



}
