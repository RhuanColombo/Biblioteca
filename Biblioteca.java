//Pessoa
public class Pessoa {
    private String nome;
    private Livro[] livros;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Livro[] getLivros() {
        return livros;
    }

    public void setLivros(Livro[] livros) {
        this.livros = livros;
    }
}

//Usuário
public class Usuario extends Pessoa {
    private int idade;
    private Emprestimo[] historicoEmprestimos;

    public Usuario(String nome, int idade) {
        super(nome);
        this.idade = idade;
        this.historicoEmprestimos = new Emprestimo[10]; // Array com capacidade para 10 empréstimos
    }

    public int getIdade() {
        return idade;
    }

    public Emprestimo[] getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        for (int i = 0; i < historicoEmprestimos.length; i++) {
            if (historicoEmprestimos[i] == null) {
                historicoEmprestimos[i] = emprestimo;
                break;
            }
        }
    }
}

//Autor
public class Autor extends Pessoa {
    private String nacionalidade;
    private Livro[] obrasPublicadas;

    public Autor(String nome, String nacionalidade) {
        super(nome);
        this.nacionalidade = nacionalidade;
        this.obrasPublicadas = new Livro[10]; // Capacidade para 10 obras
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public Livro[] getObrasPublicadas() {
        return obrasPublicadas;
    }

    public void adicionarObra(Livro livro) {
        for (int i = 0; i < obrasPublicadas.length; i++) {
            if (obrasPublicadas[i] == null) {
                obrasPublicadas[i] = livro;
                break;
            }
        }
    }

    public Livro[] getObrasPublicadasPorGenero(String genero) {
        return obrasPublicadas; // Apenas um exemplo para simplificar
    }
}

//Livro
public class Livro {
    private String titulo;
    private Autor autor;
    private String genero;
    private boolean isDisponivel;

    public Livro(String titulo, Autor autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isDisponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponivel() {
        return isDisponivel;
    }

    public void emprestar() {
        this.isDisponivel = false;
    }

    public void devolver() {
        this.isDisponivel = true;
    }
}

//Empréstimo
import java.util.Date;
public class Emprestimo {
    private Date dataRetirada;
    private Date dataDevolucao;
    private Livro livro;
    private Usuario usuario;

    public Emprestimo(Date dataRetirada, Livro livro, Usuario usuario) {
        this.dataRetirada = dataRetirada;
        this.livro = livro;
        this.usuario = usuario;
        this.dataDevolucao = null;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void registrarDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        livro.devolver(); // Disponibiliza o livro novamente
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

//Main
import java.util.Date;
public class Main {
    public static void main(String[] args) {
        // Cadastrando autor
        Autor autor = new Autor("Jessica Felix", "Brasil");

        // Cadastrando livro
        Livro livro = new Livro("Java for Beginners", autor, "Tecnologia");
        autor.adicionarObra(livro);

        // Cadastrando usuário
        Usuario usuario = new Usuario("Lucas Rafael", 25);

        // Realizando um empréstimo
        Emprestimo emprestimo = new Emprestimo(new Date(), livro, usuario);
        usuario.adicionarEmprestimo(emprestimo);
        livro.emprestar();

        // Exibindo informações
        System.out.println("Idade: " + usuario.getIdade());
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getNome());
        System.out.println("Gênero: " + livro.getGenero());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("O livro está disponível? " + (livro.isDisponivel() ? "Sim" : "Não"));

        // Registrar devolução
        emprestimo.registrarDevolucao(new Date());
        System.out.println("O livro está disponível após a devolução? " + (livro.isDisponivel() ? "Sim" : "Não"));
    }
}

