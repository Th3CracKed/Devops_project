package dependencies_injection;

import dagger.BindsInstance;
import dagger.Component;
import data_structure.Column;

import javax.inject.Named;
import java.util.List;

@Component(modules={ ColumnModule.class })
public interface ColumnComponent {

    Column getColumn();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder setCells(@Named("cells") List cells);

        ColumnComponent build();
    }
}
