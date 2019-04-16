package dependencies_injection;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import data_structure.Column;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ColumnModule_ProvideColumnFactory implements Factory<Column> {
  private final ColumnModule module;

  private final Provider<List> cellsProvider;

  public ColumnModule_ProvideColumnFactory(ColumnModule module, Provider<List> cellsProvider) {
    this.module = module;
    this.cellsProvider = cellsProvider;
  }

  @Override
  public Column get() {
    return provideInstance(module, cellsProvider);
  }

  public static Column provideInstance(ColumnModule module, Provider<List> cellsProvider) {
    return proxyProvideColumn(module, cellsProvider.get());
  }

  public static ColumnModule_ProvideColumnFactory create(
      ColumnModule module, Provider<List> cellsProvider) {
    return new ColumnModule_ProvideColumnFactory(module, cellsProvider);
  }

  public static Column proxyProvideColumn(ColumnModule instance, List cells) {
    return Preconditions.checkNotNull(
        instance.provideColumn(cells), "Cannot return null from a non-@Nullable @Provides method");
  }
}
