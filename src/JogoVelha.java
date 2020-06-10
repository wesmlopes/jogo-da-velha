import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JogoVelha extends JFrame {

    ImageIcon iconeCirculo = new ImageIcon(getClass().getResource("x.png"));
    ImageIcon iconeX = new ImageIcon(getClass().getResource("circle.png"));

    JPanel pTela = new JPanel(new GridLayout(3, 3, 10, 10));
    Bloco[] blocos = new Bloco[9];

    int rodadas = 0;

    final int jogador1 = 1;
    final int jogador2 = 2;

    int vezJogador = jogador1;

    JLabel lInformacao = new JLabel("Jogador "+jogador1);

    public JogoVelha() {
        configurarJanela();
        configurarTela();
    }

    public void configurarTela() {
        add(BorderLayout.CENTER, pTela);
        add(BorderLayout.NORTH, lInformacao);
        pTela.setBackground(Color.BLACK);
        lInformacao.setFont(new Font("HGMaruGothicMPRO", Font.BOLD,25));;
        lInformacao.setForeground(Color.BLACK);
        lInformacao.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i=0;i<9;i++) {
            Bloco bloco = new Bloco();
            blocos[i] = bloco;
            pTela.add(bloco);
        }
    }

    public void mudarVez() {
        if (vezJogador == 1) {
            vezJogador = 2;
            lInformacao.setText("Jogador 2");
            lInformacao.setForeground(Color.RED);
        } else {
            vezJogador = 1;
            lInformacao.setText("Jogador 1");
            lInformacao.setForeground(Color.BLUE);
        }
    }

    public boolean testeVitoria(int jogadores) {
        if (blocos[0].quemClicou == jogadores && blocos[1].quemClicou == jogadores && blocos[2].quemClicou == jogadores) {
            return true;
        }
        if (blocos[3].quemClicou == jogadores && blocos[4].quemClicou == jogadores && blocos[5].quemClicou == jogadores) {
            return true;
        }
        if (blocos[6].quemClicou == jogadores && blocos[7].quemClicou == jogadores && blocos[8].quemClicou == jogadores) {
            return true;
        } else
        if (blocos[0].quemClicou == jogadores && blocos[3].quemClicou == jogadores && blocos[6].quemClicou == jogadores) {
            return true;
        }
        if (blocos[1].quemClicou == jogadores && blocos[4].quemClicou == jogadores && blocos[7].quemClicou == jogadores) {
            return true;
        }
        if (blocos[2].quemClicou == jogadores && blocos[5].quemClicou == jogadores && blocos[8].quemClicou == jogadores) {
            return true;
        }
        if (blocos[0].quemClicou == jogadores && blocos[4].quemClicou == jogadores && blocos[8].quemClicou == jogadores) {
            return true;
        }
        if (blocos[2].quemClicou == jogadores && blocos[4].quemClicou == jogadores && blocos[6].quemClicou == jogadores) {
            return true;
        }
        return false;
    }

    public void configurarJanela() {
        setTitle("Jogo da Velha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JogoVelha();
    }

    public class Bloco extends JButton{
        int quemClicou = 0;
        public Bloco() {
            setBackground(Color.WHITE);
            addActionListener(e->{
                if (quemClicou==0) {
                    if (vezJogador == jogador1){
                        setIcon(iconeCirculo);
                        quemClicou = jogador1;
                    }  else {
                        setIcon(iconeX);
                        quemClicou = jogador2;
                    }
                    if (testeVitoria(quemClicou)) {
                        JOptionPane.showMessageDialog(null,"Jogador "+quemClicou+" venceu!");
                        System.exit(0);
                    }
                    rodadas++;
                    if (rodadas == 9) {
                        JOptionPane.showMessageDialog(null,"Ninguém venceu... Deu velha!");
                    }
                    mudarVez();
                }
            });
        }
    }
}

