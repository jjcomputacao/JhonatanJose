import javax.swing.*; // Fornece classes para a criação de interfaces gráficas.
import java.awt.*; // Fornece classes para a criação de interfaces gráficas.
import java.awt.event.ActionEvent; // Trata eventos de ação.
import java.awt.event.ActionListener; // Define interfaces para tratamento de eventos.
import java.sql.Connection; // Fornece recursos para acessar um banco de dados.
import java.sql.DriverManager; // Gerencia drivers de banco de dados.
import java.sql.PreparedStatement; // Representa uma instrução SQL pré-compilada.
import java.sql.SQLException; // Lida com exceções relacionadas ao SQL.

public class Banco {
    Private String url = "jdbc:mysql://localhost:3306/estoque";
    Private String usuario = "root";
    Private String senhaBanco = "senaisp";
    public void conectarBanco () {
        try {
            Connection connection = DriverManager.getConnection(this.url, this.usuario, this.senhaBanco);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível conectar ao banco de dados.");
        }
    }
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


            }
        });

        // Adicione o painel à janela
        frame.add(panel);

        // Tornar a janela visível
        frame.setVisible(true);
    }
}