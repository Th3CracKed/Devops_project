package dependencies_injection;

import dagger.Module;
import dagger.Provides;
import data_structure.Column;

import javax.inject.Named;
import java.util.List;

@Module
public class ColumnModule {
    @Provides
    List provideCells(@Named("cells") List cells){
        return cells;
    }

    @Provides
    Column provideColumn(@Named("cells") List cells){
        return new Column(cells);
    }
}
