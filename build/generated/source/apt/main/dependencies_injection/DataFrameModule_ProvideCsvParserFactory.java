package dependencies_injection;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import utils.CsvParser;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DataFrameModule_ProvideCsvParserFactory implements Factory<CsvParser> {
  private final DataFrameModule module;

  private final Provider<String> fileNameProvider;

  public DataFrameModule_ProvideCsvParserFactory(
      DataFrameModule module, Provider<String> fileNameProvider) {
    this.module = module;
    this.fileNameProvider = fileNameProvider;
  }

  @Override
  public CsvParser get() {
    return provideInstance(module, fileNameProvider);
  }

  public static CsvParser provideInstance(
      DataFrameModule module, Provider<String> fileNameProvider) {
    return proxyProvideCsvParser(module, fileNameProvider.get());
  }

  public static DataFrameModule_ProvideCsvParserFactory create(
      DataFrameModule module, Provider<String> fileNameProvider) {
    return new DataFrameModule_ProvideCsvParserFactory(module, fileNameProvider);
  }

  public static CsvParser proxyProvideCsvParser(DataFrameModule instance, String fileName) {
    return Preconditions.checkNotNull(
        instance.provideCsvParser(fileName),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
