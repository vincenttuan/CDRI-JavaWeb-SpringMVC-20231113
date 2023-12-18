package mvc.bean.spform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseData {
	
	protected Integer id;
	protected String name;
	
}
