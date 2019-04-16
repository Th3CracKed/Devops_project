package dependencies_injection;

import dagger.BindsInstance;
import dagger.Component;
import data_structure.DataFrame;

import javax.inject.Named;


@Component(modules = {DataFrameModule.class} )
public interface DataFrameComponent {

    DataFrame getDataFrame();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder fileName(@Named("file name") String fileName);

        DataFrameComponent build();
    }
}
