package dependencies_injection;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ColumnModule_ProvideCellsFactory implements Factory<List> {
  private final ColumnModule module;

  private final Provider<List> cellsProvider;

  public ColumnModule_ProvideCellsFactory(ColumnModule module, Provider<List> cellsProvider) {
    this.module = module;
    this.cellsProvider = cellsProvider;
  }

  @Override
  public List get() {
    return provideInstance(module, cellsProvider);
  }

  public static List provideInstance(ColumnModule module, Provider<List> cellsProvider) {
    return proxyProvideCells(module, cellsProvider.get());
  }

  public static ColumnModule_ProvideCellsFactory create(
      ColumnModule module, Provider<List> cellsProvider) {
    return new ColumnModule_ProvideCellsFactory(module, cellsProvider);
  }

  public static List proxyProvideCells(ColumnModule instance, List cells) {
    return Preconditions.checkNotNull(
        instance.provideCells(cells), "Cannot return null from a non-@Nullable @Provides method");
  }
}
