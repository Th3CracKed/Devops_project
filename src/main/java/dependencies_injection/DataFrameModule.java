package dependencies_injection;

import dagger.Module;
import dagger.Provides;
import data_structure.DataFrame;
import utils.CsvParser;

import javax.inject.Named;
import java.io.IOException;

@Module
public class DataFrameModule {

    @Provides
    CsvParser provideCsvParser(@Named("file name") String fileName){
        try {
            return new CsvParser(fileName);
        }catch (IOException e){
            return null;
        }
    }

    @Provides
    DataFrame provideDataFrame(CsvParser csvParser){
        return new DataFrame(csvParser);
    }
    
}
