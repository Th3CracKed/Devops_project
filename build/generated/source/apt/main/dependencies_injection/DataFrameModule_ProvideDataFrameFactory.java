package dependencies_injection;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import data_structure.DataFrame;
import javax.annotation.Generated;
import javax.inject.Provider;
import utils.CsvParser;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DataFrameModule_ProvideDataFrameFactory implements Factory<DataFrame> {
  private final DataFrameModule module;

  private final Provider<CsvParser> csvParserProvider;

  public DataFrameModule_ProvideDataFrameFactory(
      DataFrameModule module, Provider<CsvParser> csvParserProvider) {
    this.module = module;
    this.csvParserProvider = csvParserProvider;
  }

  @Override
  public DataFrame get() {
    return provideInstance(module, csvParserProvider);
  }

  public static DataFrame provideInstance(
      DataFrameModule module, Provider<CsvParser> csvParserProvider) {
    return proxyProvideDataFrame(module, csvParserProvider.get());
  }

  public static DataFrameModule_ProvideDataFrameFactory create(
      DataFrameModule module, Provider<CsvParser> csvParserProvider) {
    return new DataFrameModule_ProvideDataFrameFactory(module, csvParserProvider);
  }

  public static DataFrame proxyProvideDataFrame(DataFrameModule instance, CsvParser csvParser) {
    return Preconditions.checkNotNull(
        instance.provideDataFrame(csvParser),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
