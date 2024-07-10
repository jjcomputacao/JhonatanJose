import javax.swing.*; // Fornece classes para a criação de interfaces gráficas.
import java.awt.*; // Fornece classes para a criação de interfaces gráficas.
import java.awt.event.ActionEvent; // Trata eventos de ação.
import java.awt.event.ActionListener; // Define interfaces para tratamento de eventos.
import java.sql.Connection; // Fornece recursos para acessar um banco de dados.
import java.sql.DriverManager; // Gerencia drivers de banco de dados.
import java.sql.PreparedStatement; // Representa uma instrução SQL pré-compilada.
import java.sql.SQLException; // Lida com exceções relacionadas ao SQL.

public class Main {

    public static void main(String[] args) {
        // Crie uma janela de diálogo Swing para o cadastro de usuário
        JFrame frame = new JFrame("Cadastro de Usuário");
        frame.setSize(450, 150); // Define o tamanho da janela (largura x altura)

        // Centraliza a janela na tela
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use um painel com layout de grade para organizar os rótulos e campos
        JPanel panel = new JPanel(new GridLayout(4, 2));

        // Campos de entrada
        JTextField nomeField = new JTextField(50);
        JTextField emailField = new JTextField(50);
        JPasswordField senhaField = new JPasswordField(50);

        // Rótulos
        JLabel nomeLabel = new JLabel("Nome: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel senhaLabel = new JLabel("Senha: ");

        // Adicione rótulos, campos de entrada ao painel
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(senhaLabel);
        panel.add(senhaField);

        // Botão de cadastro
        JButton cadastrarButton = new JButton("Cadastrar");

        // Adicione o botão de cadastro ao painel
        panel.add(cadastrarButton);

        // Evento de clique do botão de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                char[] senhaChars = senhaField.getPassword();
                String senha = new String(senhaChars);

                // Informações de conexão com o banco de dados MySQL
                String url = "jdbc:mysql://localhost:3306/estoque";
                String usuario = "root";
                String senhaBanco = "senaisp";

                try {
                    // Estabelecer uma conexão com o banco de dados
                    Connection connection = DriverManager.getConnection(url, usuario, senhaBanco);

                    // Criar uma declaração SQL para a inserção de dados
                    String sql = "INSERT INTO produto (nome, email, senha) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    // Definir os parâmetros da declaração SQL
                    preparedStatement.setString(1, nome);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, senha);

                    // Executar a inserção no banco de dados
                    int linhasAfetadas = preparedStatement.executeUpdate();

                    // Verificar se a inserção foi bem-sucedida
                    if (linhasAfetadas > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

                        // Limpar os campos após a inserção
                        nomeField.setText("");
                        emailField.setText("");
                        senhaField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao cadastrar o usuário.");
                    }

                    // Fechar a conexão
                    preparedStatement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro: Não foi possível conectar ao banco de dados.");
                }
            }
        });

        // Adicione o painel à janela
        frame.add(panel);

        // Tornar a janela visível
        frame.setVisible(true);
    }
}