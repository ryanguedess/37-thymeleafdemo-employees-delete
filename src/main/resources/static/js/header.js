class Header extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    this.innerHTML = `
      <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="id">
                    <div class="navbar-nav">
                        <a class="navbar-brand" th:href="@{/clientes/list}">Clientes</a>
                        <a class="navbar-brand" th:href="@{/funcionarios/list}">Funcionários</a>
                        <a class="navbar-brand" th:href="@{/fornecedores/list}">Fornecedores</a>
                        <a class="navbar-brand" th:href="@{/fornecedores/list}">Fornecedores</a>
                        <a class="navbar-brand" th:href="@{/produtos/list}">Produtos</a>
                    </div>
                </div>
            </div>
        </nav>
      </header>
    `;
  }
}

customElements.define('header-component', Header);