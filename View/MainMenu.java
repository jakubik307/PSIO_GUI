package View;

import Controller.Controller;
import Model.Action;
import Model.Osoby.Lekarz;
import Model.Osoby.Osoba;
import Model.Osoby.Pacjent;
import Model.Osoby.Pracownik;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainMenu implements Window {
    private static JTable tableStatic;

    private JPanel rootPanel;
    private JButton wyswietlPracownikowButton;
    private JButton wyswietlPacjentowButton;
    private JButton wyswietlWszystkichButton;
    private JButton dodajLekarzaButton;
    private JButton dodajPielegniarkeButton;
    private JButton dodajPacjentaButton;
    private JButton zglosChorobeButton;
    private JButton leczeniePacjentaButton;
    private JButton szczepieniePacjentaButton;
    private JButton wyswietlSzczepieniaButton;
    private JButton wyszukiwanieButton;
    private JTable table1;
    private JPanel buttonPanel;
    private JPanel ScrollPanel;

    public MainMenu() {
        tableStatic = table1;

//       Model.Action.stanPoczatkowy();
        Controller.wczytajDane();

        //Wstawienie danych do tabeli
        Controller.setTable(table1, 3);

        //Centrowanie danych w kolumnie
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(String.class, centerRenderer);
        table1.setDefaultRenderer(Integer.class, centerRenderer);
        table1.setDefaultRenderer(Long.class, centerRenderer);

        //Action listeners
        wyswietlPracownikowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setTable(table1, 1);
            }
        });

        wyswietlPacjentowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setTable(table1, 2);
            }
        });

        wyswietlWszystkichButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setTable(table1, 3);
            }
        });

        dodajLekarzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new AddLekarz());
            }
        });

        dodajPielegniarkeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new AddPielegniarka());
            }
        });

        dodajPacjentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new AddPacjent());
            }
        });

        zglosChorobeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new AddChoroba());
            }
        });

        leczeniePacjentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new Leczenie());
            }
        });

        szczepieniePacjentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new Szczepienie());
            }
        });

        wyswietlSzczepieniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new ListaSzczepien());
            }
        });

        wyszukiwanieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.openWindow(new Wyszukiwanie());
            }
        });
    }

    public static JTable getTableStatic() {
        return tableStatic;
    }

    @Override
    public void GUI_Create() {
        JFrame frame = new JFrame("Szpital");
        frame.setContentPane(new MainMenu().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("logo.png").getImage());


        //Saving on window close
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Action.zapiszDane();
                super.windowClosing(e);
            }
        });
    }

    public static class OsobaTableModel extends AbstractTableModel {


        private final String[] COLUMNS = {"Typ", "Imie", "Nazwisko", "Pesel", "Wiek"};

        private ArrayList<Osoba> osoby;

        public OsobaTableModel(ArrayList<Osoba> osoby) {
            this.osoby = osoby;
        }

        public void setOsoby(ArrayList<Osoba> osoby) {
            this.osoby = osoby;
        }

        @Override
        public int getRowCount() {
            return osoby.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> osoby.get(rowIndex).getClass().getSimpleName();
                case 1 -> osoby.get(rowIndex).getImie();
                case 2 -> osoby.get(rowIndex).getNazwisko();
                case 3 -> osoby.get(rowIndex).getPesel();
                case 4 -> osoby.get(rowIndex).getWiek();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }
    }

    public static class PacjentTableModel extends AbstractTableModel {

        private final String[] COLUMNS = {"Typ", "Imie", "Nazwisko", "Pesel", "Wiek", "Stan zdrowia", "Koszty leczenia"};

        private ArrayList<Osoba> osoby;

        public PacjentTableModel(ArrayList<Osoba> osoby) {
            this.osoby = osoby;
        }

        public void setOsoby(ArrayList<Osoba> osoby) {
            this.osoby = osoby;
        }

        @Override
        public int getRowCount() {
            return osoby.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return osoby.get(rowIndex).getClass().getSimpleName();
                case 1:
                    return osoby.get(rowIndex).getImie();
                case 2:
                    return osoby.get(rowIndex).getNazwisko();
                case 3:
                    return osoby.get(rowIndex).getPesel();
                case 4:
                    return osoby.get(rowIndex).getWiek();
                case 5:
                    if (osoby.get(rowIndex) instanceof Pacjent) {
                        if (((Pacjent) osoby.get(rowIndex)).isChory()) {
                            return "chory";
                        } else {
                            return "zdrowy";
                        }
                    } else {
                        return "-";
                    }
                case 6:
                    if (osoby.get(rowIndex) instanceof Pacjent) {
                        return ((Pacjent) osoby.get(rowIndex)).getKosztyLeczenia();
                    } else {
                        return "-";
                    }
                default:
                    return "-";
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }
    }

    public static class PracownikTableModel extends AbstractTableModel {

        private final String[] COLUMNS = {"Typ", "Imie", "Nazwisko", "Pesel", "Wiek", "Pensja", "Specjalizacja"};
        private ArrayList<Osoba> osoby;


        public PracownikTableModel(ArrayList<Osoba> osoby) {
            this.osoby = osoby;
        }

        public void setOsoby(ArrayList<Osoba> osoby) {
            this.osoby = osoby;
        }

        @Override
        public int getRowCount() {
            return osoby.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return osoby.get(rowIndex).getClass().getSimpleName();
                case 1:
                    return osoby.get(rowIndex).getImie();
                case 2:
                    return osoby.get(rowIndex).getNazwisko();
                case 3:
                    return osoby.get(rowIndex).getPesel();
                case 4:
                    return osoby.get(rowIndex).getWiek();
                case 5:
                    if (osoby.get(rowIndex) instanceof Pracownik) {
                        return ((Pracownik) osoby.get(rowIndex)).getPensja();
                    } else {
                        return 0;
                    }
                case 6:
                    if (osoby.get(rowIndex) instanceof Lekarz) {
                        return ((Lekarz) osoby.get(rowIndex)).getSpecjalizacja();
                    } else {
                        return "-";
                    }
                default:
                    return "-";
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }
    }
}
