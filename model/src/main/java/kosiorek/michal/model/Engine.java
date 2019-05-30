package kosiorek.michal.model;


import kosiorek.michal.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engine {

private EngineType type;
private double power;


}
