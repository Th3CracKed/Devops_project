package dependencies_injection;

import dagger.internal.Preconditions;
import data_structure.DataFrame;
import javax.annotation.Generated;
import utils.CsvParser;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerDataFrameComponent implements DataFrameComponent {
  private DataFrameModule dataFrameModule;

  private String fileName;

  private DaggerDataFrameComponent(Builder builder) {
    initialize(builder);
  }

  public static DataFrameComponent.Builder builder() {
    return new Builder();
  }

  private CsvParser getCsvParser() {
    return DataFrameModule_ProvideCsvParserFactory.proxyProvideCsvParser(dataFrameModule, fileName);
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.dataFrameModule = builder.dataFrameModule;
    this.fileName = builder.fileName;
  }

  @Override
  public DataFrame getDataFrame() {
    return DataFrameModule_ProvideDataFrameFactory.proxyProvideDataFrame(
        dataFrameModule, getCsvParser());
  }

  private static final class Builder implements DataFrameComponent.Builder {
    private DataFrameModule dataFrameModule;

    private String fileName;

    @Override
    public DataFrameComponent build() {
      if (dataFrameModule == null) {
        this.dataFrameModule = new DataFrameModule();
      }
      if (fileName == null) {
        throw new IllegalStateException(String.class.getCanonicalName() + " must be set");
      }
      return new DaggerDataFrameComponent(this);
    }

    @Override
    public Builder fileName(String fileName) {
      this.fileName = Preconditions.checkNotNull(fileName);
      return this;
    }
  }
}
