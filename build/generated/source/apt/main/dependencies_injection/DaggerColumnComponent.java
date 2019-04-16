package dependencies_injection;

import dagger.internal.Preconditions;
import data_structure.Column;
import java.util.List;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerColumnComponent implements ColumnComponent {
  private ColumnModule columnModule;

  private List setCells;

  private DaggerColumnComponent(Builder builder) {
    initialize(builder);
  }

  public static ColumnComponent.Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.columnModule = builder.columnModule;
    this.setCells = builder.setCells;
  }

  @Override
  public Column getColumn() {
    return ColumnModule_ProvideColumnFactory.proxyProvideColumn(columnModule, setCells);
  }

  private static final class Builder implements ColumnComponent.Builder {
    private ColumnModule columnModule;

    private List setCells;

    @Override
    public ColumnComponent build() {
      if (columnModule == null) {
        this.columnModule = new ColumnModule();
      }
      if (setCells == null) {
        throw new IllegalStateException(List.class.getCanonicalName() + " must be set");
      }
      return new DaggerColumnComponent(this);
    }

    @Override
    public Builder setCells(List cells) {
      this.setCells = Preconditions.checkNotNull(cells);
      return this;
    }
  }
}
