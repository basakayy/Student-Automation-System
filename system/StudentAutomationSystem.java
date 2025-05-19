package automation.system;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StudentAutomationSystem {
    public static void main(String[] args) {
        // Kullanıcıları ekle
        UserDatabase.addUser(new Student("1", "1", "Başak Ay"));
        UserDatabase.addUser(new Instructor("2", "2", "Nursena Özkan"));

        SwingUtilities.invokeLater(StudentAutomationSystem::createLoginScreen);
    }
    
    public static void openRegisterUserWindow() {
        JFrame registerFrame = new JFrame("Yeni Kullanıcı Kaydı");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(600, 450);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Üst Kısım: Başlık
        JLabel titleLabel = new JLabel("Yeni Kullanıcı Kaydı", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Orta Kısım: Giriş Alanları
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Kullanıcı ID:");
        JTextField idField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Şifre:");
        JPasswordField passwordField = new JPasswordField(20);
        JLabel nameLabel = new JLabel("Ad Soyad:");
        JTextField nameField = new JTextField(20);
        JLabel typeLabel = new JLabel("Kullanıcı Türü:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Student", "Instructor"});

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(typeComboBox, gbc);

        // Alt Kısım: Butonlar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout(15, 10));

        JButton registerButton = new JButton("Kaydet");
        JButton cancelButton = new JButton("İptal");

        registerButton.setPreferredSize(new Dimension(120, 30));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(cancelButton, BorderLayout.WEST);
        buttonPanel.add(registerButton, BorderLayout.EAST);

        // Renkler ve Kenarlıklar
        mainPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Frame Yapısı
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        registerFrame.add(mainPanel);

        // İşlevsellik
        registerButton.addActionListener(e -> {
            String userID = idField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String userType = (String) typeComboBox.getSelectedItem();

            if (UserDatabase.addNewUser(userID, password, name, userType)) {
                JOptionPane.showMessageDialog(registerFrame, "Kullanıcı başarıyla kaydedildi.");
                registerFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(registerFrame, "Kullanıcı kaydedilemedi. Bilgileri kontrol edin.");
            }
        });

        cancelButton.addActionListener(e -> registerFrame.dispose());

        registerFrame.setVisible(true);
    }

    
    public static void createLoginScreen() {
        JFrame loginFrame = new JFrame("Öğrenci İşleri Otomasyonu - Giriş");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(450, 350);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Üst Kısım: Başlık
        JLabel titleLabel = new JLabel("Öğrenci İşleri Otomasyon Sistemi", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Orta Kısım: Giriş Alanları
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Kullanıcı ID:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Şifre:");
        JPasswordField passField = new JPasswordField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(passField, gbc);

        // Alt Kısım: Butonlar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout(15, 10));

        JButton loginButton = new JButton("Giriş Yap");
        JButton registerButton = new JButton("Yeni Kullanıcı Kaydı");
        JButton exitButton = new JButton("Çıkış");

        loginButton.setPreferredSize(new Dimension(120, 30));
        registerButton.setPreferredSize(new Dimension(150, 30));
        exitButton.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(exitButton, BorderLayout.WEST);
        buttonPanel.add(registerButton, BorderLayout.CENTER);
        buttonPanel.add(loginButton, BorderLayout.EAST);

        // Renkler ve Kenarlıklar
        mainPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Frame Yapısı
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        loginFrame.add(mainPanel);

        // İşlevsellik
        loginButton.addActionListener(e -> {
            String userID = userField.getText();
            String password = new String(passField.getPassword());
            User user = UserDatabase.getUser(userID);

            if (user != null && user instanceof Authentication authUser && authUser.login(userID, password)) {
                JOptionPane.showMessageDialog(loginFrame, "Giriş başarılı!");
                loginFrame.dispose();

                if (user instanceof Student student) {
                    createStudentDashboard(student);
                    Login login = new Studentlogin();//referans üzerinden sadece Login sınıfındaki metotlara erişilebilir.
                    login.loginprint(); // Sisteme giriş yapıldı.
                    ((Studentlogin) login).specificLoginAction();/* login değişkeni, Studentlogin türüne dönüştürülüyor.
                     Öğrenci özel giriş işlemi yapıldı.*/
                } 
                else if (user instanceof Instructor instructor) {
                	 Login login = new Instructorlogin();
                     login.loginprint(); // Sisteme giriş yapıldı.
                     ((Instructorlogin) login).specificLoginAction(); // Öğretmen özel giriş işlemi yapıldı.
                    createInstructorDashboard(instructor);
                }
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Geçersiz kullanıcı ID veya şifre.");
            }
        });

        registerButton.addActionListener(e -> openRegisterUserWindow());

        exitButton.addActionListener(e -> System.exit(0));

        loginFrame.setVisible(true);
    }

    public static void openChangePasswordWindow(User user) {
        JFrame changePasswordFrame = new JFrame("Şifre Değiştir");
        changePasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changePasswordFrame.setSize(400, 200);
        changePasswordFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel currentPassLabel = new JLabel("Mevcut Şifre:");
        JPasswordField currentPassField = new JPasswordField();
        JLabel newPassLabel = new JLabel("Yeni Şifre:");
        JPasswordField newPassField = new JPasswordField();

        JButton saveButton = new JButton("Kaydet");
        JButton cancelButton = new JButton("İptal");

        changePasswordFrame.add(currentPassLabel);
        changePasswordFrame.add(currentPassField);
        changePasswordFrame.add(newPassLabel);
        changePasswordFrame.add(newPassField);
        changePasswordFrame.add(cancelButton);
        changePasswordFrame.add(saveButton);

        saveButton.addActionListener(e -> {
            String currentPass = new String(currentPassField.getPassword());
            String newPass = new String(newPassField.getPassword());

            if (user.password.equals(currentPass)) {
                user.changePassword(newPass);
                JOptionPane.showMessageDialog(changePasswordFrame, "Şifre başarıyla değiştirildi.");
                changePasswordFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(changePasswordFrame, "Mevcut şifre hatalı!");
            }
        });

        cancelButton.addActionListener(e -> changePasswordFrame.dispose());

        changePasswordFrame.setVisible(true);
    }

    
    public static void createStudentDashboard(Student student) {
        JFrame dashboard = new JFrame("Öğrenci Paneli");
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setSize(450, 350);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Üst Kısım: Hoş Geldiniz Mesajı
        JLabel welcomeLabel = new JLabel("Hoş geldiniz, " + student.name + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Orta Kısım: İşlev Butonları
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton registerCourseButton = new JButton("Ders Seçimi");
        JButton viewCoursesButton = new JButton("Dersleri Görüntüle");
        JButton viewGradesButton = new JButton("Notlarımı Görüntüle");
        JButton viewProfileButton = new JButton("Profilimi Görüntüle");
        JButton viewExamsButton = new JButton("Sınavlarımı Görüntüle");
        JButton changePasswordButton = new JButton("Şifre Değiştir");
        JButton generateReportButton = new JButton("Rapor Oluştur");
        JButton logoutButton = new JButton("Çıkış");

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(registerCourseButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(viewCoursesButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(viewGradesButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(viewProfileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(viewExamsButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonPanel.add(changePasswordButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3; 
        buttonPanel.add(generateReportButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        buttonPanel.add(logoutButton, gbc);

        // Renkler ve Kenarlıklar
        mainPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Frame Yapısı
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        dashboard.add(mainPanel);

        // İşlevsellik
        registerCourseButton.addActionListener(e -> openCourseSelectionWindow(student));

        viewCoursesButton.addActionListener(e -> {
            StringBuilder courses = new StringBuilder("Kayıtlı Dersler:\n");
            student.registeredCourses.keySet().forEach(course -> courses.append(course).append("\n"));
            JOptionPane.showMessageDialog(dashboard, courses.toString());
        });

        viewGradesButton.addActionListener(e -> openGradeViewWindow(student));

        viewProfileButton.addActionListener(e -> viewStudentProfile(student));

        viewExamsButton.addActionListener(e -> viewStudentExams(student));
        
        changePasswordButton.addActionListener(e -> openChangePasswordWindow(student));

        logoutButton.addActionListener(e -> {
            dashboard.dispose();
            createLoginScreen();
        });
        
        generateReportButton.addActionListener(e -> {
            student.generateReport(); // Öğrenci sınıfındaki metodu çağır
            JOptionPane.showMessageDialog(dashboard, "Rapor konsolda oluşturuldu.");
        });
        dashboard.setVisible(true);
    }

    
    public static void openCourseSelectionWindow(Student student) {
        JFrame courseFrame = new JFrame("Ders Seçimi");
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        courseFrame.setSize(400, 400);
        courseFrame.setLayout(new BorderLayout());

        // Ders Listesi (Checkbox'lar)
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new GridLayout(8, 1));

        JCheckBox mathCheckbox = new JCheckBox("Matematik");
        JCheckBox physicsCheckbox = new JCheckBox("Fizik");
        JCheckBox turkishCheckbox = new JCheckBox("Türkçe");
        JCheckBox socialCheckbox = new JCheckBox("Sosyal");
        JCheckBox peCheckbox = new JCheckBox("Beden");
        JCheckBox musicCheckbox = new JCheckBox("Müzik");
        JCheckBox biologyCheckbox = new JCheckBox("Biyoloji");
        JCheckBox chemistryCheckbox = new JCheckBox("Kimya");

        coursePanel.add(mathCheckbox);
        coursePanel.add(physicsCheckbox);
        coursePanel.add(turkishCheckbox);
        coursePanel.add(socialCheckbox);
        coursePanel.add(peCheckbox);
        coursePanel.add(musicCheckbox);
        coursePanel.add(biologyCheckbox);
        coursePanel.add(chemistryCheckbox);

        // Onay ve İptal Butonları
        JButton confirmButton = new JButton("Onayla");
        JButton cancelButton = new JButton("İptal");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        courseFrame.add(new JLabel("Ders Seçimi Yapın:", JLabel.CENTER), BorderLayout.NORTH);
        courseFrame.add(coursePanel, BorderLayout.CENTER);
        courseFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Onaylama İşlevi
        confirmButton.addActionListener(e -> {
            StringBuilder selectedCourses = new StringBuilder("Seçilen Dersler:\n");

            if (mathCheckbox.isSelected()) {
                student.registerCourse("Matematik");
                selectedCourses.append("Matematik\n");
            }
            if (physicsCheckbox.isSelected()) {
                student.registerCourse("Fizik");
                selectedCourses.append("Fizik\n");
            }
            if (turkishCheckbox.isSelected()) {
                student.registerCourse("Türkçe");
                selectedCourses.append("Türkçe\n");
            }
            if (socialCheckbox.isSelected()) {
                student.registerCourse("Sosyal");
                selectedCourses.append("Sosyal\n");
            }
            if (peCheckbox.isSelected()) {
                student.registerCourse("Beden");
                selectedCourses.append("Beden\n");
            }
            if (musicCheckbox.isSelected()) {
                student.registerCourse("Müzik");
                selectedCourses.append("Müzik\n");
            }
            if (biologyCheckbox.isSelected()) {
                student.registerCourse("Biyoloji");
                selectedCourses.append("Biyoloji\n");
            }
            if (chemistryCheckbox.isSelected()) {
                student.registerCourse("Kimya");
                selectedCourses.append("Kimya\n");
            }

            if (selectedCourses.length() > 15) {
                JOptionPane.showMessageDialog(courseFrame, selectedCourses.toString());
                courseFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(courseFrame, "Hiçbir ders seçilmedi.");
            }
        });

        // İptal İşlevi
        cancelButton.addActionListener(e -> courseFrame.dispose());

        courseFrame.setVisible(true);
    }

    public static void createInstructorDashboard(Instructor instructor) {
        JFrame dashboard = new JFrame("Öğretim Üyesi Paneli");
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setSize(450, 350);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Üst Kısım: Hoş Geldiniz Mesajı
        JLabel welcomeLabel = new JLabel("Hoş geldiniz, " + instructor.name + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Orta Kısım: İşlev Butonları
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton addStudentButton = new JButton("Öğrenci Ekle");
        JButton viewStudentsButton = new JButton("Öğrencileri Görüntüle");
        JButton gradeEntryButton = new JButton("Not Girişi");
        JButton examDefinitionButton = new JButton("Sınav Tanımlama");
        JButton approveCoursesButton = new JButton("Ders Onayı");
        JButton assignAdvisorButton = new JButton("Danışman Atama");
        JButton scheduleEntryButton = new JButton("Ders Programı Girişi");
        JButton changePasswordButton = new JButton("Şifre Değiştir");
        JButton deleteUserButton = new JButton("Kullanıcı Sil");
        JButton generateReportButton = new JButton("Rapor Oluştur");
        JButton logoutButton = new JButton("Çıkış");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addStudentButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(viewStudentsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(gradeEntryButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(examDefinitionButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(approveCoursesButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonPanel.add(assignAdvisorButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(scheduleEntryButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        buttonPanel.add(deleteUserButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        buttonPanel.add(changePasswordButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        buttonPanel.add(generateReportButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        buttonPanel.add(logoutButton, gbc);
                
        // Renkler ve Kenarlıklar
        mainPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Frame Yapısı
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        dashboard.add(mainPanel);

        // İşlevsellik
        addStudentButton.addActionListener(e -> openAddStudentWindow());

        viewStudentsButton.addActionListener(e -> openViewStudentsWindow());

        gradeEntryButton.addActionListener(e -> openGradeEntryWindow());

        assignAdvisorButton.addActionListener(e -> assignAdvisorToStudent());

        examDefinitionButton.addActionListener(e -> openExamDefinitionWindow());
        
        scheduleEntryButton.addActionListener(e -> openScheduleWindow());
        
        changePasswordButton.addActionListener(e -> openChangePasswordWindow(instructor));

        approveCoursesButton.addActionListener(e -> {
            String studentId = JOptionPane.showInputDialog("Derslerini onaylayacağınız öğrenci ID'sini girin:");
            User user = UserDatabase.getUser(studentId);
            if (user instanceof Student student) {
                approveCourses(student);
            } else {
                JOptionPane.showMessageDialog(dashboard, "Geçersiz Öğrenci ID.");
            }
        });
        
        deleteUserButton.addActionListener(e -> {
            String userID = JOptionPane.showInputDialog("Silmek istediğiniz Kullanıcı ID'sini girin:");
            if (UserDatabase.removeUser(userID)) {
                JOptionPane.showMessageDialog(null, "Kullanıcı başarıyla silindi.");
            } else {
                JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı.");
            }
        });
        
        generateReportButton.addActionListener(e -> {
            instructor.generateReport();
            JOptionPane.showMessageDialog(dashboard, "Sınıf başarı raporu konsolda oluşturuldu.");
        });

        logoutButton.addActionListener(e -> {
            dashboard.dispose();
            createLoginScreen();
        });

        dashboard.setVisible(true);
    }
    
    public static void openAddStudentWindow() {
        JFrame addStudentFrame = new JFrame("Öğrenci Ekle");
        addStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStudentFrame.setSize(400, 300);
        addStudentFrame.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("Öğrenci ID:");
        JTextField idField = new JTextField();
        JLabel passwordLabel = new JLabel("Şifre:");
        JPasswordField passwordField = new JPasswordField();
        JLabel nameLabel = new JLabel("Ad Soyad:");
        JTextField nameField = new JTextField();

        JButton addButton = new JButton("Ekle");
        JButton cancelButton = new JButton("İptal");

        addStudentFrame.add(idLabel);
        addStudentFrame.add(idField);
        addStudentFrame.add(passwordLabel);
        addStudentFrame.add(passwordField);
        addStudentFrame.add(nameLabel);
        addStudentFrame.add(nameField);
        addStudentFrame.add(addButton);
        addStudentFrame.add(cancelButton);

        addButton.addActionListener(e -> {
            String userID = idField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();

            if (userID.isEmpty() || password.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(addStudentFrame, "Tüm alanları doldurun.");
            } else if (UserDatabase.addStudent(userID, password, name)) {
                JOptionPane.showMessageDialog(addStudentFrame, "Öğrenci başarıyla eklendi.");
                addStudentFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addStudentFrame, "Bu ID'ye sahip bir öğrenci zaten var.");
            }
        });

        cancelButton.addActionListener(e -> addStudentFrame.dispose());

        addStudentFrame.setVisible(true);
    }
    public static void openViewStudentsWindow() {
        JFrame viewStudentsFrame = new JFrame("Öğrenci Listesi");
        viewStudentsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewStudentsFrame.setSize(400, 300);

        JTextArea studentList = new JTextArea();
        studentList.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(studentList);

        // Başlık kısmı
        JLabel headerLabel = new JLabel("Kayıtlı Öğrenciler:", JLabel.CENTER);

        // Tüm öğrencileri listele
        StringBuilder students = new StringBuilder();
        UserDatabase.users.values().stream()
            .filter(user -> user instanceof Student) // Sadece öğrencileri filtrele
            .forEach(user -> {
                Student student = (Student) user;
                students.append("ID: ").append(student.userID)
                        .append(", İsim: ").append(student.name).append("\n");
            });

        if (students.length() == 0) {
            studentList.setText("Henüz kayıtlı öğrenci yok.");
        } else {
            studentList.setText(students.toString());
        }

        // Frame düzeni
        viewStudentsFrame.setLayout(new BorderLayout());
        viewStudentsFrame.add(headerLabel, BorderLayout.NORTH);
        viewStudentsFrame.add(scrollPane, BorderLayout.CENTER);

        viewStudentsFrame.setVisible(true);
    }
    public static void assignAdvisorToStudent() {
        JFrame assignFrame = new JFrame("Danışman Atama");
        assignFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        assignFrame.setSize(400, 200);
        assignFrame.setLayout(new GridLayout(3, 2));

        JLabel studentIdLabel = new JLabel("Öğrenci ID:");
        JTextField studentIdField = new JTextField();
        JLabel advisorNameLabel = new JLabel("Danışman Adı:");
        JTextField advisorNameField = new JTextField();

        JButton assignButton = new JButton("Ata");
        JButton cancelButton = new JButton("İptal");

        assignFrame.add(studentIdLabel);
        assignFrame.add(studentIdField);
        assignFrame.add(advisorNameLabel);
        assignFrame.add(advisorNameField);
        assignFrame.add(assignButton);
        assignFrame.add(cancelButton);

        assignButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String advisorName = advisorNameField.getText();

            User user = UserDatabase.getUser(studentId);
            User advisor = UserDatabase.getUserByName(advisorName);

            if (user instanceof Student student && advisor instanceof Instructor instructor) {
                student.setAdvisor(instructor); // Danışman ata
                JOptionPane.showMessageDialog(assignFrame, "Danışman başarıyla atandı!");
                assignFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(assignFrame, "Geçersiz öğrenci veya danışman bilgisi!");
            }
        });

        cancelButton.addActionListener(e -> assignFrame.dispose());

        assignFrame.setVisible(true);
    }

    public static void openGradeEntryWindow() {
        JFrame gradeFrame = new JFrame("Not Girişi");
        gradeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gradeFrame.setSize(400, 300);
        gradeFrame.setLayout(new GridLayout(4, 2));

        JLabel studentIdLabel = new JLabel("Öğrenci ID:");
        JTextField studentIdField = new JTextField();
        JLabel courseLabel = new JLabel("Ders Ismi:");
        JTextField courseField = new JTextField();
        JLabel gradeLabel = new JLabel("Not:");
        JTextField gradeField = new JTextField();

        JButton submitButton = new JButton("Kaydet");
        JButton cancelButton = new JButton("İptal");

        gradeFrame.add(studentIdLabel);
        gradeFrame.add(studentIdField);
        gradeFrame.add(courseLabel);
        gradeFrame.add(courseField);
        gradeFrame.add(gradeLabel);
        gradeFrame.add(gradeField);
        gradeFrame.add(submitButton);
        gradeFrame.add(cancelButton);

        submitButton.addActionListener(e -> {
            String studentId = studentIdField.getText();
            String courseCode = courseField.getText();
            String grade = gradeField.getText();

            User user = UserDatabase.getUser(studentId);
            if (user instanceof Student student) {
                // Öğrencinin kayıtlı derslerini kontrol et
                if (student.registeredCourses.containsKey(courseCode)) {
                    student.registeredCourses.put(courseCode, grade); // Notu ekle
                    JOptionPane.showMessageDialog(gradeFrame, "Not başarıyla kaydedildi.");
                    gradeFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(gradeFrame, "Öğrenci bu dersi almıyor!");
                }
            } else {
                JOptionPane.showMessageDialog(gradeFrame, "Geçersiz Öğrenci ID.");
            }
        });

        cancelButton.addActionListener(e -> gradeFrame.dispose());

        gradeFrame.setVisible(true);
    }
 // Tarih kontrol metodu
    private static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static void approveCourses(Student student) {
        if (student.registeredCourses.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Onaylanacak ders bulunmamaktadır.");
        } else {
            student.coursesApproved = true;
            JOptionPane.showMessageDialog(null, "Dersler başarıyla onaylandı.");
        }
    }

    public static void openGradeViewWindow(Student student) {
        JFrame gradeViewFrame = new JFrame("Notlarım");
        gradeViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gradeViewFrame.setSize(400, 300);

        JTextArea gradeList = new JTextArea();
        gradeList.setEditable(false);

        StringBuilder grades = new StringBuilder();
        student.registeredCourses.forEach((course, grade) -> 
            grades.append(course).append(": ").append(grade).append("\n"));

        if (grades.length() == 0) {
            gradeList.setText("Henüz not kaydı bulunmamaktadır.");
        } else {
            gradeList.setText(grades.toString());
        }

        gradeViewFrame.setLayout(new BorderLayout());
        gradeViewFrame.add(new JScrollPane(gradeList), BorderLayout.CENTER);

        gradeViewFrame.setVisible(true);
    }

    public static void openExamDefinitionWindow() {
        JFrame examFrame = new JFrame("Sınav Tanımlama");
        examFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        examFrame.setSize(400, 300);
        examFrame.setLayout(new GridLayout(3, 2));

        JLabel courseLabel = new JLabel("Ders Ismi:");
        JTextField courseField = new JTextField();
        JLabel dateLabel = new JLabel("Sınav Tarihi (dd.MM.yyyy):");
        JTextField dateField = new JTextField();

        JButton saveButton = new JButton("Kaydet");
        JButton cancelButton = new JButton("İptal");

        examFrame.add(courseLabel);
        examFrame.add(courseField);
        examFrame.add(dateLabel);
        examFrame.add(dateField);
        examFrame.add(saveButton);
        examFrame.add(cancelButton);

        saveButton.addActionListener(e -> {
            String course = courseField.getText();
            String date = dateField.getText();

            if (!course.isEmpty() && !date.isEmpty()) {
                // Geçerli ders kontrolü
                if (CourseDatabase.isCourseValid(course)) {
                    // Tarih formatını kontrol et
                    if (isValidDate(date)) {
                        ExamDatabase.addExam(course, date);
                        JOptionPane.showMessageDialog(examFrame, "Sınav başarıyla tanımlandı: " + course + " - " + date);
                        examFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(examFrame, "Geçersiz tarih formatı. Tarihi dd.MM.yyyy formatında girin.");
                    }
                } else {
                    JOptionPane.showMessageDialog(examFrame, "Geçersiz ders ismi. Lütfen mevcut bir ders girin.");
                }
            } else {
                JOptionPane.showMessageDialog(examFrame, "Tüm alanları doldurun.");
            }
        });

        cancelButton.addActionListener(e -> examFrame.dispose());

        examFrame.setVisible(true);
    }


    public static void viewStudentExams(Student student) {
        JFrame examFrame = new JFrame("Sınavlarım");
        examFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        examFrame.setSize(400, 300);

        JTextArea examList = new JTextArea();
        examList.setEditable(false);

        StringBuilder exams = new StringBuilder("Sınavlar:\n");
        for (String course : student.registeredCourses.keySet()) {
            ArrayList<Exam> courseExams = ExamDatabase.getExamsForCourse(course);
            for (Exam exam : courseExams) {
                exams.append(exam.toString()).append("\n");
            }
        }

        if (exams.length() == 8) { // Sadece "Sınavlar:\n" varsa
            exams.append("Henüz sınav bulunmamaktadır.");
        }

        examList.setText(exams.toString());

        examFrame.setLayout(new BorderLayout());
        examFrame.add(new JScrollPane(examList), BorderLayout.CENTER);

        examFrame.setVisible(true);
    }

    public static void openScheduleWindow() {
        JFrame scheduleFrame = new JFrame("Ders Programı");
        scheduleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scheduleFrame.setSize(400, 300);

        JTextArea scheduleArea = new JTextArea();
        scheduleArea.setEditable(true);

        JButton saveButton = new JButton("Kaydet");
        scheduleFrame.setLayout(new BorderLayout());
        scheduleFrame.add(new JScrollPane(scheduleArea), BorderLayout.CENTER);
        scheduleFrame.add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            String schedule = scheduleArea.getText();
            JOptionPane.showMessageDialog(scheduleFrame, "Program kaydedildi:\n" + schedule);
        });

        scheduleFrame.setVisible(true);
    }

    public static void viewStudentProfile(Student student) {
        JFrame profileFrame = new JFrame("Profilim");
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setSize(400, 300);

        JTextArea profileDetails = new JTextArea();
        profileDetails.setEditable(false);

        String advisorName = (student.getAdvisor() != null) ? student.getAdvisor().name : "Henüz atanmadı";

        profileDetails.setText("Ad Soyad: " + student.name + "\n"
                              + "ID: " + student.userID + "\n"
                              + "Kayıtlı Dersler: " + student.registeredCourses.keySet() + "\n"
                              + "Danışman: " + advisorName);

        profileFrame.setLayout(new BorderLayout());
        profileFrame.add(new JScrollPane(profileDetails), BorderLayout.CENTER);

        profileFrame.setVisible(true);
    }



}
