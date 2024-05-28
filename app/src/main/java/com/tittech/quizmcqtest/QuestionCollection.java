package com.tittech.quizmcqtest;

import static com.tittech.quizmcqtest.R.id.opcaoA;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionCollection extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String rightAnswerFull;
    String Answer;
    public static List<QuestionModule> question_list;
    int score;
    int wrong;
    public static String SUBJECT_NAME = "";
    public static ArrayList <ArrayList<QuestionModule>> questionBank = new ArrayList<>();
    public static ArrayList <HashMap<String, String>> subjectList = new ArrayList<>();
    LinearLayout rootLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_collection);
        rootLay = findViewById(R.id.rootLay);
        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);
        loadQuestion();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        });

    }


/*    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        if(rootLay!=null) rootLay.startAnimation(AnimationUtils.loadAnimation(QuestionCollection.this, R.anim.middle_to_top));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loadQuestion(){
//        Toast.makeText(getApplicationContext(), "Total Questions: "+question_list.size(), Toast.LENGTH_SHORT).show();
        if(question_list.size() > 0) {
            QuestionModule q = question_list.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));
            rightAnswer = q.getRightAnswer();
            rightAnswerFull = q.getRightAnswerFull();

        } else {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("wrong", wrong);
            startActivity(intent);
            finish();
        }
    }


    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadQuestion();
                }

            }, 2*1000);


        }else {
            this.wrong += 1;
            screen = new Intent(this, WrongActivity.class);
            screen.putExtra("lblQuestion",lblQuestion.getText().toString());
            screen.putExtra("rightAnswer",rightAnswer);
            screen.putExtra("rightAnswerFull",rightAnswerFull);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadQuestion();
                }

            }, 2*1000);

        }

        return screen;
    }



    //===============================================================================================







    //====================================================================
    //====================================================================
    public static  ArrayList <QuestionModule> questions;
    public static void createQuestionBank(){
        QuestionCollection.subjectList = new ArrayList<>();
        QuestionCollection.questionBank = new ArrayList<>();


        //------------- 1-10
                questions = new ArrayList(){
            {
                add(new QuestionModule("n what year was the first iPhone released?", "A", "2007","2005", "2007", "2008", "2010"));
                add(new QuestionModule("Who wrote the novel 1984?", "A", "George Orwell","George Orwell", "J.K. Rowling", "F. Scott Fitzgerald", "Ernest Hemingway"));
                add(new QuestionModule("What is the capital city of Australia?", "C", "Canberra ","Sydney", "Melbourne", "Canberra ", "Brisbane"));
                add(new QuestionModule("What is the chemical symbol for Gold?", "D", "Au ","Gd", "Go", "Ag", "Au"));
                add(new QuestionModule("What is the tallest mountain in the world?", "B", "Mount Everest","K2", "Mount Everest", "Mount Kilimanjaro", "Denali"));
                add(new QuestionModule("Who painted the Mona Lisa?", "A", "Leonardo da Vinci ","Leonardo da Vinci", "Michelangelo", "Raphael", "Caravaggio"));
                add(new QuestionModule("Which planet is known as the Red Planet?", "B", "Mars","Venus", "Mars", "Jupiter", "Saturn"));
                add(new QuestionModule("Who discovered electricity?", "D", "Benjamin Franklin","Isaac Newton", "Nikola Tesla", "Michael Faraday", "Benjamin Franklin"));
                add(new QuestionModule("What is the world's largest ocean?", "C", "Pacific Ocean","Atlantic Ocean", "Indian Ocean", "Pacific Ocean", "Southern Ocean"));
                add(new QuestionModule("Who came up with the theory of relativity?", "B", "Albert Einstein","Edgar Allan Poe", "Albert Einstein", "Galileo Galilei", "Louis Pasteur"));
            }
        };
        QuestionModule.createQuestionsForSubject("01 To 10", questions);


        //------------- 11-20
        questions = new ArrayList(){
            {
                add(new QuestionModule("What is the national bird of the United States?", "A", "Eagle","Eagle", "Bald Eagle", "Condor", "Pigeon"));
                add(new QuestionModule("What language is spoken in Brazil?", "B", "Portuguese ","Spanish", "Portuguese ", "English", "French"));
                add(new QuestionModule("Who directed the movie Jurassic Park?", "A", "Steven Spielberg","Steven Spielberg", "George Lucas", "Michael Bay", "Stanley Kubrick"));
                add(new QuestionModule("What is sushi traditionally wrapped in?", "B", "Seaweed","Rice Paper", "Seaweed", "Bamboo", "Lettuce"));
                add(new QuestionModule("What is the main ingredient in hummus?", "C", "Chickpeas ","Potatoes", "Lentils", "Chickpeas ", "White Beans"));
                add(new QuestionModule("Who is the author of the Harry Potter series?", "D", "J.K. Rowling ","J.D. Salinger", "Roald Dahl", "Suzanne Collins", "J.K. Rowling"));
                add(new QuestionModule("How many players are there in a soccer team?", "B", "11","6", "11", "9", "4"));
                add(new QuestionModule("What does a barometer measure?", "C", "Atmospheric Pressure","Sound", "Light", "Atmospheric Pressure", "Humidity"));
                add(new QuestionModule("What is the highest-grossing film of all time?", "C", "Avengers: Endgame","Titanic", "Avatar", "Avengers: Endgame", "Star Wars: The Force Awakens"));
                add(new QuestionModule("In what decade was the Internet created?", "A", "1960s","1960s", "1970s", "1980s", "1990s"));
            }
        };
        QuestionModule.createQuestionsForSubject("21 To 20", questions);




        //------------- 21-30
        questions = new ArrayList(){
            {
                add(new QuestionModule("The Hwang Ho River falls into?", "B", "Yellow Sea","Indian Ocean", "Yellow Sea", "Red Sea", "Bay of China"));
                add(new QuestionModule("Yokohama and Kobe are the main centres of ship building industry in?", "A", "Japan","Japan", "South Korea", "North Korea", "China"));
                add(new QuestionModule("Cocos plate lies between?", "A", "Central America and Pacific Plate.","Central America and Pacific Plate.", "North America and Pacific Plate.", "South America and Pacific Plate.", "None of Above"));
                add(new QuestionModule("Brown Waterfalls are situated in?", "B", "New Zealand","Australia", "New Zealand", "Canada", "SwitSwitzerland"));
                add(new QuestionModule("Mindanoo Island is located in?", "C", "The Pacific Ocean","The Indian Ocean", "North Atlantic", "The Pacific Ocean", "South Atlantic"));
                add(new QuestionModule("Upper Volta is the old name of?", "C", "Burkina Faso","Angola", "Cape Verde", "Burkina Faso", "Burkina"));
                add(new QuestionModule("Greenland is under the rule of?", "B", "Denmark","Turkey", "Denmark", "Germany", "Portugal"));
                add(new QuestionModule("The highest point of Australia is?", "A", "Mount Kosciusko.","Mount Kosciusko.", "Mount Twonsend", "Mount Twynam", "Abbott Peak"));
                add(new QuestionModule("Who constructed Suez Canal?", "D", "Ferdinand de Lesseps","Fyodor Litke", "Pyotr Semyonov-Tyan-Shansky", "Voin Rimsky-Korsakov", "Ferdinand de Lesseps"));
                add(new QuestionModule("Cincinnati (U .S .A.) is famous in the making of?", "A", "Machine Tools","Machine Tools", "Cement Industry", "Bio-technical Industry", "Aviation Tools"));
            }
        };
        QuestionModule.createQuestionsForSubject("21 To 30", questions);


        //------------- 31-40
        questions = new ArrayList(){
            {
                add(new QuestionModule("To scale Mount Everest, mountaineers need to go to?", "C", "Nepal","Afghanistan", "Bhutan", "Nepal", "Myanmar"));
                add(new QuestionModule("New Delhi's Lotus Temple was designed by an architect from?", "B", "Iran ","Germany ", "Iran ", "UAE", "Japan "));
                add(new QuestionModule("In Vienna, there's a statue of Indian hockey player _____ holding four hockey sticks in four Hands ?", "A", "Dhyan Chand","Dhyan Chand ", "Dhanraj Pillay ", "Udham Singh Kular", "Bharat Kumar Chettri"));
                add(new QuestionModule("Which of the following is the second largest artificial lake in Asia ?", "D", " Dhebar Lake in Rajasthan ","Chilika Lake in Odisha", "Chandubi Lake in Assam ", "Kolleru Lake in Andhra Pradesh", " Dhebar Lake in Rajasthan"));
                add(new QuestionModule("The National Game of Bhutan is ?", "A", "archery ","archery ", "shooting ", "taekwondo ", "wrestling "));
                add(new QuestionModule("Which country is called the ‘Coffee Bowl of the World’ ?", "D", "Brazil ","Serbia ", "Mexico ", "India ", "Brazil "));
                add(new QuestionModule("What was the magnitude of the Indian Ocean Tsunami 2004 ?", "C", "9.1 ","7.4 ", "8.9 ", "9.1 ", "8.6"));
                add(new QuestionModule("World's largest field hockey stadium based on the seating capacity is located in which Country ?", "B", "Pakistan ","Australia ", "Pakistan ", "Netherlands ", "India"));
                add(new QuestionModule("Which of the following statements about Sambhar lake is true ?", "D", "It is the largest inland salt lake in India. ","It is the highest lake in India. ", "It was formed due to the hypervelocity impact of a comet.", "It drains into the Arabian sea. ", "It is the largest inland salt lake in India. "));
                add(new QuestionModule("Which is the largest uranium producing country in the world ?", "B", "Kazakhstan  ","Uzbekistana ", "Kazakhstan", "USA ", "India "));
            }
        };
        QuestionModule.createQuestionsForSubject("31 To 40", questions);


        //------------- 41-50
        questions = new ArrayList(){
            {
                add(new QuestionModule("Where are the Great Pyramids of Giza located? ", "A", "West bank of the Nile River","West bank of the Nile River", "East bank of the Nile River", "Sahara Desert", "Giza Plateau"));
                add(new QuestionModule("What writing system did the Mayans develop?", "C", "Maya hieroglyphs","Aztec script", "Incan quipus", "Aztec script", "Zapotec script"));
                add(new QuestionModule("Which ancient empire was known for its vast network of roads and aqueducts?", "B", "Roman","Egyptian", "Roman", "Assyrian", "Chinese"));
                add(new QuestionModule("What was the name of the devastating plague that swept through Europe in the 14th century?", "D", "The Black Death","The Great Famine", "The Hundred Years' War", "The Great Schism", "The Black Death"));
                add(new QuestionModule("Who invented the printing press, revolutionising the way knowledge was shared?", "B", "Johannes Gutenberg","William Caxton", "Johannes Gutenberg", "Wang Chen", "Bi Sheng"));
                add(new QuestionModule("What year did the Berlin Wall fall, symbolising the end of the Cold War?", "C", "1989","1961", "1985", "1989", "1991"));
                add(new QuestionModule("Which global organisation was formed after World War II to promote international cooperation and peace?", "A", "United Nations","United Nations", "World Health Organisation", "League of Nations", "North Atlantic Treaty Organisation (NATO)"));
                add(new QuestionModule("Who led the French Revolution and became the Emperor of France?", "B", "Napoleon Bonaparte","Louis XVI", "Napoleon Bonaparte", "Maximilien Robespierre", "Jacques-Pierre Brissot"));
                add(new QuestionModule("Which country gained independence from the British Raj in 1947?", "D", " Both A and B","India", "Pakistan", "Bangladesh", " Both A and B"));
                add(new QuestionModule("Which inventor is most closely associated with the creation of the Rocket, a steam-powered railway engine?", "B", "George Stephenson","Richard Trevithick", "George Stephenson", "Isambard Brunel", "James Watt"));
            }
        };
        QuestionModule.createQuestionsForSubject("41 To 50", questions);

        //------------- 51-60
        questions = new ArrayList(){
            {

                add(new QuestionModule("Which year did World War I begin?", "D","1914", "1905", "1917", "1921", "1914"));
                add(new QuestionModule("Who was the first President of the United States?", "D","George Washington", "Thomas Jefferson", "John Adams", "Abraham Lincoln", "George Washington"));
                add(new QuestionModule("What year did Christopher Columbus arrive in the Americas?", "D","1492", "1520", "1456", "1510", "1492"));
                add(new QuestionModule("Which ancient civilization built the pyramids?", "A","Ancient Egyptians", "Ancient Greeks", "Ancient Romans", "Mayans", "Ancient Egyptians"));
                add(new QuestionModule("Who was the first Emperor of Rome?", "C","Augustus", "Nero", "Constantine", "Augustus", "Julius Caesar"));
                add(new QuestionModule("Who painted the Mona Lisa?", "B","Leonardo da Vinci", "Michelangelo", "Leonardo da Vinci" ,"Raphael", "Donatello"));
                add(new QuestionModule("Which country was the first to use paper money?", "B","China" , "China","India", "Italy", "Egypt"));
                add(new QuestionModule("In which year did the American Civil War end?", "D","1865", "1876", "1850", "1888", "1865"));
                add(new QuestionModule("Which scientist formulated the theory of relativity?", "D","Albert Einstein", "Isaac Newton", "Galileo Galilei", "Stephen Hawking", "Albert Einstein"));
                add(new QuestionModule("Who was the first woman to win a Nobel Prize?", "A","Marie Curie", "Marie Curie","Rosalind Franklin", "Ada Lovelace", "Jane Goodall" ));

            }
        };
        QuestionModule.createQuestionsForSubject("51 To 60", questions);


        //------------- 61-70
        questions = new ArrayList(){
            {

                add(new QuestionModule("Who wrote 'To Kill a Mockingbird'?", "A","Harper Lee", "Harper Lee","Mark Twain", "J.D. Salinger", "F. Scott Fitzgerald"));
                add(new QuestionModule("Which city is considered the birthplace of democracy?", "D","Athens", "Rome", "Paris", "London", "Athens"));
                add(new QuestionModule("Who painted the ceiling of the Sistine Chapel?", "C","Michelangelo", "Leonardo da Vinci", "Raphael", "Michelangelo", "Donatello"));
                add(new QuestionModule("Which battle is considered the turning point of World War II in Europe?", "A","Battle of Stalingrad", "Battle of Stalingrad", "Battle of Midway", "Battle of Normandy", "Battle of Britain"));
                add(new QuestionModule("Who was the first person to step on the moon?", "B","Neil Armstrong", "Buzz Aldrin", "Neil Armstrong", "Yuri Gagarin", "Alan Shepard"));
                add(new QuestionModule("Which city was the first to be attacked by an atomic bomb?", "A","Hiroshima", "Hiroshima","Nagasaki", "Tokyo", "Berlin"));
                add(new QuestionModule("Who was the first female Prime Minister of the United Kingdom?", "A","Margaret Thatcher", "Margaret Thatcher", "Theresa May", "Angela Merkel", "Indira Gandhi"));
                add(new QuestionModule("Who is known as the 'Father of the Computer'?", "B","Charles Babbage", "Alan Turing", "Bill Gates", "Charles Babbage", "Steve Jobs"));
                add(new QuestionModule("Which country was the first to launch an artificial satellite into space?", "D","Soviet Union (Russia)", "United States", "China", "India", "Soviet Union (Russia)"));
                add(new QuestionModule("Who wrote 'The Communist Manifesto'?", "D","Karl Marx and Friedrich Engels", "Vladimir Lenin", "Leon Trotsky", "Joseph Stalin", "Karl Marx and Friedrich Engels"));

            }
        };
        QuestionModule.createQuestionsForSubject("61 To 70", questions);


        //------------- 71-80
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who was the longest-reigning monarch in British history?", "A","Queen Elizabeth II", "Queen Elizabeth II", "King George III", "King Henry VIII", "Queen Victoria"));
                add(new QuestionModule("What year did the Berlin Wall fall?", "D","1989", "1991", "1979", "1990", "1989"));
                add(new QuestionModule("Which civilization built the Great Wall of China?", "D","Ancient Chinese", "Ancient Romans", "Ancient Greeks", "Mayans", "Ancient Chinese"));
                add(new QuestionModule("Who was the first female President of a country?", "B","Sirimavo Bandaranaike", "Golda Meir", "Sirimavo Bandaranaike", "Indira Gandhi", "Margaret Thatcher"));
                add(new QuestionModule("Which war is also known as the 'War to End All Wars'?", "C","World War I", "World War II", "Vietnam War", "World War I", "Korean War"));
                add(new QuestionModule("Who discovered penicillin?", "A","Alexander Fleming", "Alexander Fleming","Louis Pasteur", "Robert Koch", "Edward Jenner"));
                add(new QuestionModule("Who painted 'The Starry Night'?", "D","Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet", "Vincent van Gogh"));
                add(new QuestionModule("Who is considered the 'Father of Medicine'?", "B","Hippocrates", "Aristotle", "Hippocrates", "Socrates", "Plato"));
                add(new QuestionModule("Which country is known as the 'Land of the Rising Sun'?", "C","Japan", "China", "Korea", "Japan", "Vietnam"));
                add(new QuestionModule("Who was the first female astronaut?", "D","Valentina Tereshkova", "Sally Ride", "Mae Jemison", "Judith Resnik", "Valentina Tereshkova"));

            }
        };
        QuestionModule.createQuestionsForSubject("71 To 80", questions);



        //------------- 81-90
        questions = new ArrayList(){
            {

                add(new QuestionModule("Which explorer was the first to circumnavigate the globe?", "D","Ferdinand Magellan", "Christopher Columbus", "Vasco da Gama", "Marco Polo", "Ferdinand Magellan"));
                add(new QuestionModule("Who was the first female pilot to fly solo across the Atlantic Ocean?", "B","Amelia Earhart", "Bessie Coleman", "Amelia Earhart", "Harriet Quimby", "Jacqueline Cochran"));
                add(new QuestionModule("Which empire was ruled by Julius Caesar?", "A","Roman Empire","Roman Empire", "Byzantine Empire", "Mongol Empire", "Ottoman Empire"));
                add(new QuestionModule("Which scientist proposed the heliocentric theory?", "D","Nicolaus Copernicus", "Galileo Galilei", "Isaac Newton", "Johannes Kepler", "Nicolaus Copernicus"));
                add(new QuestionModule("Who wrote 'The Diary of a Young Girl', also known as 'The Diary of Anne Frank'?", "B","Anne Frank", "Margaret Atwood", "Anne Frank", "Harper Lee", "Maya Angelou"));
                add(new QuestionModule("Who invented the telephone?", "C","Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Alexander Graham Bell", "Guglielmo Marconi"));
                add(new QuestionModule("Which European city is known as the 'City of Love'?", "A","Paris", "Paris", "Rome", "Venice", "Florence"));
                add(new QuestionModule("Who founded Microsoft?", "D","Bill Gates and Paul Allen", "Steve Jobs and Steve Wozniak", "Larry Page and Sergey Brin", "Mark Zuckerberg", "Bill Gates and Paul Allen"));
                add(new QuestionModule("In which year did the Titanic sink?", "B","1912", "1914", "1912", "1916", "1918"));
                add(new QuestionModule("Which Chinese philosopher wrote 'The Art of War'?", "C","Sun Tzu", "Confucius", "Laozi", "Sun Tzu", "Mencius"));

            }
        };
        QuestionModule.createQuestionsForSubject("81 To 90", questions);


        //------------- 91-100
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who painted 'The Last Supper'?", "C","Leonardo da Vinci", "Michelangelo", "Raphael", "Leonardo da Vinci", "Donatello"));
                add(new QuestionModule("Which country hosted the first modern Olympic Games?", "A","Greece", "Greece", "France", "United States", "Germany"));
                add(new QuestionModule("Who composed 'Symphony No. 9'?", "D","Ludwig van Beethoven", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "Johannes Brahms", "Ludwig van Beethoven"));
                add(new QuestionModule("Who was the first European explorer to reach India by sea?", "B","Vasco da Gama", "Christopher Columbus", "Vasco da Gama", "Ferdinand Magellan", "Marco Polo"));
                add(new QuestionModule("Which famous speech began with 'I have a dream'?", "D","Martin Luther King Jr.'s speech", "John F. Kennedy's speech", "Winston Churchill's speech", "Nelson Mandela's speech", "Martin Luther King Jr.'s speech"));
                add(new QuestionModule("Who was the first emperor of China?", "B","Qin Shi Huang", "Han Wudi", "Qin Shi Huang", "Kangxi Emperor", "Taizong of Tang"));
                add(new QuestionModule("Who was the first President of the Soviet Union?", "A","Mikhail Gorbachev", "Mikhail Gorbachev", "Joseph Stalin", "Leonid Brezhnev", "Vladimir Lenin"));
                add(new QuestionModule("Which ocean did Ferdinand Magellan first navigate?", "A","Pacific Ocean", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"));
                add(new QuestionModule("Which queen ruled England for only nine days?", "C","Lady Jane Grey", "Queen Mary I", "Queen Elizabeth I", "Lady Jane Grey", "Mary Queen of Scots"));
                add(new QuestionModule("Who wrote 'The Wealth of Nations'?", "D","Adam Smith", "Karl Marx", "John Maynard Keynes", "Milton Friedman", "Adam Smith"));

            }
        };
        QuestionModule.createQuestionsForSubject("91 To 100", questions);









        //------------- 101-110
        questions = new ArrayList(){
            {
                add(new QuestionModule("Badgirs’ are chimney-like structures, which are ingeniously designed in which country?", "A", "Iran","Iran", "Israel", "UAE", "Japan"));
                add(new QuestionModule("Which country has employed the ‘Cabbage strategy’?", "B", "China","Ukraine", "China", "Afghanistan", "Myanmar"));
                add(new QuestionModule("Norway, Germany and Finland reported which disease on fur farms and mammals?", "B", "Avian Influenza","Anthrax", "Avian Influenza", "Foot and mouth disease", "Blue tongue"));
                add(new QuestionModule("Which Asian country recorded a 27% rise in tiger population since 2015?", "C", "Bhutan","India", "Sri Lanka", "Bhutan", "Nepal"));
                add(new QuestionModule("Which country has decided to suspend the operation of its Taipan helicopter fleet?", "C", "Australia","USA", "Russia", "Australia", "China"));
                add(new QuestionModule("Port Moresby is the capital of which country?", "B", "Papua New Guinea","Indonesia", "Papua New Guinea", "Philippines", "Malta"));
                add(new QuestionModule("Which institution has launched the ‘Good Manufacturing Practices (GMP)?", "B", "WHO","IMF", "WHO", "World Bank", "WTO"));
                add(new QuestionModule("Luna-25’ is the lunar lander developed by which country?", "A", "Russia","Russia", "Germany", "USA", "UAE"));
                add(new QuestionModule("Which country has objected to the incorporation of the Sanskrit phrase ‘Vasudhaiva Kutumbakam’ in the G-20 logo?", "B", "China","Bangladesh", "China", "Iran", "Russia"));
                add(new QuestionModule("Which country launched the ‘Sponge City Initiative’ in 2015?", "D", "China","Japan", "USA", "Australia", "China"));
            }
        };
        QuestionModule.createQuestionsForSubject("101 To 110", questions);

        //------------- 111-120
        questions = new ArrayList(){
            {
                add(new QuestionModule("‘MPOWER Measures’, which are seen in the news, are associated with which organisation?", "B", "World Health Organization","UN Women", "World Health Organization", "World Bank", "International Monetary Fund"));
                add(new QuestionModule("Which country released the ‘Guidelines for the Construction of Minor Mode of the Mobile Internet’?", "A", "China","China", "Russia", "USA", "UAE"));
                add(new QuestionModule("Which country released the draft regulations governing facial recognition technology usage?", "B", "China","India", "China", "UK", "USA"));
                add(new QuestionModule("Reliance Jio recently completed the work related to connecting which country with a High-Speed Optic Fibre cable?", "D", "Maldives","Cambodia", "Indonesia", "Sri Lanka", "Maldives"));
                add(new QuestionModule("Which International development financial institution has planned to issue Indian rupee bond?", "A", "New Development Bank","New Development Bank", "Asian Development Bank", "International Monetary Fund", "World Bank"));
                add(new QuestionModule("Which European country has announced plans to ban disposable electronic cigarettes?", "C", "France","Spain", "Germany", "France", "Italy"));
                add(new QuestionModule("Which country is set to introduce legislation to tighten loopholes in workplace law?", "D", "Australia","India", "Canada", "Japan", "Australia"));
                add(new QuestionModule("Medal of the City of Paris’ has been revoked from whom?", "A", "Mahmoud Abbas","Mahmoud Abbas", "Michael Jackson", "Rafael Nadal", "Hocine Ziani"));
                add(new QuestionModule("Flying Taj Mahal’ is the nickname of the aircraft of which country’s Prime Minister?", "D", "Canada","USA", "India", "UK", "Canada"));
                add(new QuestionModule("Which is set to become the first country to engage in commercial deep sea mining?", "C", "Norway","India", "Japan", "Norway", "USA"));
            }
        };
        QuestionModule.createQuestionsForSubject("111 To 120", questions);

        //------------- 121-130
        questions = new ArrayList(){
            {
                add(new QuestionModule("What is the tallest mountain in the world, measured from base to peak?", "B", "Mauna Kea","Mount Everest ", "Mauna Kea", "Mount Chimborazo", "Denali"));
                add(new QuestionModule("The Grand Canyon, a natural wonder carved by the Colorado River, is located in which US state?", "A", "Arizona","Arizona", "California ", "Colorado ", "Nevada "));
                add(new QuestionModule("Which of the following seas is the world's smallest and shallowest?", "D", "Sea of Azov","Arctic Ocean ", "Baltic Sea", "Mediterranean Sea", "Sea of Azov"));
                add(new QuestionModule("The iconic White House serves as the official residence of the President of the United States. In which city is it located?", "C", "Washington, D.C.","New York City", "Los Angeles", "Washington, D.C.", "Chicago"));
                add(new QuestionModule(" The Great Wall of China, a UNESCO World Heritage Site, stretches for thousands of miles through which country?", "D", "China ","Japan", "Korea", "India ", "China "));
                add(new QuestionModule("The iconic Taj Mahal, a monument to love and architectural marvel, is located in which Indian city?", "B", "Agra","Mumbai", "Agra", "Delhi", "Kolkata"));
                add(new QuestionModule("Which of the following is the Capital of Poland?", "B", "Warsaw","Berlin", "Warsaw", "Paris ", "Dublin"));
                add(new QuestionModule("Which country produces the most maple syrup in the world?", "A", "Canada","Canada", "United States", "Germany", "Australia"));
                add(new QuestionModule("The Nile, which is one of the longest rivers in the world, has its source in which African country?", "B", "Ethiopia","Egypt", "Ethiopia", "Sudan", "Algeria"));
                add(new QuestionModule("Which country boasts the highest number of natural lakes in the world?", "C", "Canada","United States", "Russia", "Canada", "Finland"));
            }
        };
        QuestionModule.createQuestionsForSubject("121 To 130", questions);

        //------------- 131-140
        questions = new ArrayList(){
            {

                add(new QuestionModule("Who founded the Red Cross?", "D","Henry Dunant", "Clara Barton", "Florence Nightingale", "Albert Schweitzer", "Henry Dunant"));
                add(new QuestionModule("Which country was ruled by the famous leader known as Attila the Hun?", "A","Huns", "Huns", "Mongolia", "Persia", "Greece"));
                add(new QuestionModule("Which Italian city is famous for its canals?", "C","Venice", "Rome", "Milan", "Venice", "Florence"));
                add(new QuestionModule("Who painted 'The Birth of Venus'?", "D","Sandro Botticelli", "Leonardo da Vinci", "Raphael", "Michelangelo", "Sandro Botticelli"));
                add(new QuestionModule("Who was the author of 'War and Peace'?", "B","Leo Tolstoy", "Fyodor Dostoevsky", "Leo Tolstoy", "Anton Chekhov", "Nikolai Gogol"));
                add(new QuestionModule("Who discovered America?", "B","Christopher Columbus", "Amerigo Vespucci", "Christopher Columbus", "Leif Erikson", "Marco Polo"));
                add(new QuestionModule("Who wrote 'The Divine Comedy'?", "A","Dante Alighieri", "Dante Alighieri", "Geoffrey Chaucer", "Miguel de Cervantes", "John Milton"));
                add(new QuestionModule("Who was the first woman to fly solo across the Atlantic Ocean?", "C","Amelia Earhart", "Bessie Coleman", "Harriet Quimby", "Amelia Earhart", "Jacqueline Cochran"));
                add(new QuestionModule("Which ancient wonder of the world was located in Alexandria, Egypt?", "D","Pharos of Alexandria (Lighthouse of Alexandria)", "Great Pyramid of Giza", "Hanging Gardens of Babylon", "Statue of Zeus at Olympia", "Pharos of Alexandria (Lighthouse of Alexandria)"));
                add(new QuestionModule("Who was the founder of Buddhism?", "C","Siddhartha Gautama (Buddha)", "Confucius", "Laozi", "Siddhartha Gautama (Buddha)", "Mahavira"));

            }
        };
        QuestionModule.createQuestionsForSubject("131 To 140", questions);

        //------------- 141-150
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which famous scientist developed the three laws of motion?", "B","Isaac Newton", "Galileo Galilei", "Isaac Newton", "Albert Einstein", "Nikola Tesla"));
                add(new QuestionModule("Who composed 'The Marriage of Figaro'?", "D","Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Johann Sebastian Bach", "Johannes Brahms", "Wolfgang Amadeus Mozart"));
                add(new QuestionModule("Who was the longest-reigning monarch in European history?", "A","Louis XIV of France", "Louis XIV of France", "Elizabeth II of England", "Victoria of England", "Frederick II of Prussia"));
                add(new QuestionModule("Who invented the World Wide Web (WWW)?", "B","Tim Berners-Lee", "Bill Gates", "Tim Berners-Lee", "Steve Jobs", "Larry Page"));
                add(new QuestionModule("Who is considered the 'Father of Modern Philosophy'?", "C","René Descartes", "Plato", "Aristotle", "René Descartes", "Immanuel Kant"));
                add(new QuestionModule("Which famous king had a legendary sword called Excalibur?", "A","King Arthur", "King Arthur", "Charlemagne", "Alexander the Great", "Richard the Lionheart"));
                add(new QuestionModule("Who founded the Mongol Empire?", "C","Genghis Khan", "Attila the Hun", "Kublai Khan", "Genghis Khan", "Timur"));
                add(new QuestionModule("Who was the first President of the United States to be impeached?", "D","Andrew Johnson", "Richard Nixon", "Bill Clinton", "Donald Trump", "Andrew Johnson"));
                add(new QuestionModule("Who is known for developing the theory of evolution by natural selection?", "B","Charles Darwin", "Gregor Mendel", "Charles Darwin", "Alfred Wallace", "Thomas Huxley"));
                add(new QuestionModule("Which scientist discovered the laws of planetary motion?", "D","Johannes Kepler", "Nicolaus Copernicus", "Galileo Galilei", "Isaac Newton", "Johannes Kepler"));


            }
        };
        QuestionModule.createQuestionsForSubject("141 To 150", questions);


        //------------- 151-160
        questions = new ArrayList(){
            {
                add(new QuestionModule("Archaeologists have unearthed 3,000-year-old priestly tomb in which country?", "A", "Peru","Peru", "Argentina", "Greece", "Japan"));
                add(new QuestionModule("Claudia Sheinbaum, who was seen in the news, is set to become the first leader of which country?", "B", "Mexico","Singapore", "Mexico", "Australia", "France"));
                add(new QuestionModule("Which country has recently rejoined the European Union’s science research programme -Horizon Europe?", "A", "U.K","U.K", "Germany", "France", "Italy"));
                add(new QuestionModule("India recently organised the meeting of Strategic Partnership Council (SPC) formed with which country?", "D", "Saudi Arabia","Brazil", "Argentina", "Egypt", "Saudi Arabia"));
                add(new QuestionModule("USA has announced Prisoner Exchange and Fund Transfer with which country?", "B", "Iran","UAE", "Iran", "Israel", "Saudi Arabia"));
                add(new QuestionModule("Bangladesh signed two bilateral instruments to enhance cooperation in  infrastructure and satellite?", "A", "France","France", "Australia", "Germany", "USA"));
                add(new QuestionModule("India decided to end disputes over the import of poultry products, with which country?", "B", "USA","Sri Lanka", "USA", "Israel", "Bangladesh"));
                add(new QuestionModule("National Hispanic Heritage Month is celebrated in which country?", "B", "USA","Australia", "USA", "France", "Germany"));
                add(new QuestionModule("Deferred Action for Childhood Arrivals (DACA) program is associated with which country?", "A", "USA","USA", "UK", "Australia", "Germany"));
                add(new QuestionModule("Which country has offered temporary work permits to nearly 500,000 Venezuelans?", "D", "USA","UK", "Australia", "Ukraine", "USA"));
            }
        };
        QuestionModule.createQuestionsForSubject("151 To 160", questions);

        //------------- 161-170
        questions = new ArrayList(){
            {
                add(new QuestionModule("Gigafactory is owned and operated by?", "C", "Tesla","Apple", "Facebook", "Tesla", "Microsoft"));
                add(new QuestionModule("The total height of Mount Everest is?", "B", " 8,848 meter","7,848 meter", " 8,848 meter", "9,848 meter", "10,848 meter"));
                add(new QuestionModule("The world population is about _____ billion.?", "B", "8","7", "8", "9", "10"));
                add(new QuestionModule("The world’s highest mountain is “Mount Everest”. The second highest mountain is?", "A", "K2","K2", "Kangchenjunga", "Cho Oyu", "Nanga Parbat"));
                add(new QuestionModule("Yangtze River is the longest river that entirely flows within one country. It flows in?", "A", "China","China", "Russia", "Canada", "New Zealand"));
                add(new QuestionModule("The city of Samarkand is located in?", "C", "Uzbekistan","Russia", "Tashkent", "Uzbekistan", "Kazakhstan"));
                add(new QuestionModule("The world’s oldest tennis venue is?", "A", "Wimbledon","Wimbledon", "Rotterdam", "Acer Arena", "None of these"));
                add(new QuestionModule("What was the old name of Sri Lanka?", "D", " All of the above","Serendib", "Ceylon", "Lankadeepa", " All of the above"));
                add(new QuestionModule("The “Order of Rising Sun” is highest Military Award of?", "C", "Japan","Russia", "South Korea", "Japan", "None of these"));
                add(new QuestionModule("Nelson Mandela International Day (or Mandela Day) is celebrated each year on?", "B", "18 July","18 June", "18 July", "18 August", "None of these"));
            }
        };
        QuestionModule.createQuestionsForSubject("161 To 170", questions);


        //------------- 171-180
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who was the first European explorer to reach the Americas?", "D","Christopher Columbus", "Ferdinand Magellan", "Vasco da Gama", "Marco Polo", "Christopher Columbus"));
                add(new QuestionModule("Who painted 'The Persistence of Memory'?", "B","Salvador Dalí", "Pablo Picasso", "Salvador Dalí", "Vincent van Gogh", "Claude Monet"));
                add(new QuestionModule("Which dynasty built the Great Wall of China?", "A","Qin Dynasty", "Qin Dynasty", "Han Dynasty", "Ming Dynasty", "Tang Dynasty"));
                add(new QuestionModule("Who wrote 'Don Quixote'?", "C","Miguel de Cervantes", "Leo Tolstoy", "Charles Dickens", "Miguel de Cervantes", "Jane Austen"));
                add(new QuestionModule("Who composed 'The Four Seasons'?", "D","Antonio Vivaldi", "Ludwig van Beethoven", "Johann Sebastian Bach", "Wolfgang Amadeus Mozart", "Antonio Vivaldi"));
                add(new QuestionModule("Who was the first female Prime Minister of India?", "A","Indira Gandhi", "Indira Gandhi", "Golda Meir", "Margaret Thatcher", "Sirimavo Bandaranaike"));
                add(new QuestionModule("Who discovered gravity?", "C","Isaac Newton", "Albert Einstein", "Galileo Galilei", "Isaac Newton", "Nikola Tesla"));
                add(new QuestionModule("Who wrote 'The Adventures of Huckleberry Finn'?", "B","Mark Twain", "Harper Lee", "Mark Twain", "J.D. Salinger", "F. Scott Fitzgerald"));
                add(new QuestionModule("Who was the first person to win two Nobel Prizes?", "D","Marie Curie", "Albert Einstein", "Winston Churchill", "Martin Luther King Jr.", "Marie Curie"));
                add(new QuestionModule("Which ancient civilization built the city of Machu Picchu?", "C","Inca Empire", "Aztec Empire", "Mayan Civilization", "Inca Empire", "Roman Empire"));

            }
        };
        QuestionModule.createQuestionsForSubject("171 To 180", questions);

        //------------- 181-190
        questions = new ArrayList(){
            {
                add(new QuestionModule("In what year did the Great October Socialist Revolution take place?", "A", "1917","1917", "1923", "1914", "1920"));
                add(new QuestionModule("What is the largest lake in the world?", "B", "Baikal","Caspian Sea", "Baikal", "Lake Superior", "Ontario"));
                add(new QuestionModule("Which planet in the solar system is known as the “Red Planet”?", "C", "Mars","Venus", "Earth", "Mars", "Jupiter"));
                add(new QuestionModule("Who wrote the novel “War and Peace”?", "C", "Leo Tolstoy","Anton Chekhov", "Fyodor Dostoevsky", "Leo Tolstoy", "Ivan Turgenev"));
                add(new QuestionModule("What is the capital of Japan?", "B", "Tokyo","Beijing", "Tokyo", "Seoul", "Bangkok"));
                add(new QuestionModule("Which river is the longest in the world?", "C", "Nile","Amazon", "Mississippi", "Nile", "Yangtze"));
                add(new QuestionModule("What gas is used to extinguish fires?", "D", "Nitrogen","Oxygen", "Carbon dioxide", "Hydrogen", "Nitrogen"));
                add(new QuestionModule("What animal is the national symbol of Australia?", "A", "Kangaroo","Kangaroo", "Koala", "Emu", "Crocodile"));
                add(new QuestionModule("Which of the following planets is not a gas giant?", "D", "Mars","Jupiter", "Saturn", "Uranus", "Mars"));
                add(new QuestionModule("What is the name of the process by which plants convert sunlight into energy?", "B", "Photosynthesis","Respiration", "Photosynthesis", "Oxidation", "Evolution"));
            }
        };
        QuestionModule.createQuestionsForSubject("181 To 190", questions);


        //------------- 191-200
        questions = new ArrayList(){
            {
                add(new QuestionModule("What chemical element is designated as Hg?", "D", "Mercury","Silver", "Tin", "Copper", "Mercury"));
                add(new QuestionModule("In what year was the first international modern Olympiad held?", "A", "1896","1896", "1900", "1912", "1924"));
                add(new QuestionModule("For which of these disciplines Nobel Prize is awarded?", "D", "All of the above","Physics, Chemistry", "Physiology", "Medicine", "All of the above"));
                add(new QuestionModule("Entomology is the science that studies:", "B", "Insects","Behavior of human beings", "Insects", "The origin and history of technical and scientific terms", "The formation of rocks"));
                add(new QuestionModule("Hitler's party is known as:", "B", "Nazi Party","Labour Party", "Nazi Party", "Ku-Klux-Klan", "Democratic Party"));
                add(new QuestionModule("For which Galileo is famous?", "D", "All of the above","Developed the telescope", "Discovered four satellites of Jupiter", "Found that the swinging motion of the pendulum results in consistent time measurement.", "All of the above"));
                add(new QuestionModule("When the First Afghan War took place in", "A", "1839","1839", "1843", "1833", "1848"));
                add(new QuestionModule("Ecology deals with", "C", "Relation between organisms and their environment","Birds", "Cell formation", "Relation between organisms and their environment", "Tissues"));
                add(new QuestionModule("Which is the largest island?", "C", "Greenland","New Guinea", "Andaman Nicobar", "Greenland", "Hawaii"));
                add(new QuestionModule("Which one is the hottest continent?", "A", "Africa","Africa", "South Asia", "North America", "Australia"));
            }
        };
        QuestionModule.createQuestionsForSubject("191 To 200", questions);

        //------------- 201-210
        questions = new ArrayList(){
            {

                add(new QuestionModule("Who was the last tsar of Russia?", "C","Nicholas II", "Alexander II", "Peter the Great", "Nicholas II", "Ivan the Terrible"));
                add(new QuestionModule("Which city was the center of the Renaissance?", "D","Florence", "Athens", "Rome", "Venice", "Florence"));
                add(new QuestionModule("Who wrote 'Les Misérables'?", "B","Victor Hugo", "Charles Dickens", "Victor Hugo", "Leo Tolstoy", "Fyodor Dostoevsky"));
                add(new QuestionModule("Who founded the theory of psychoanalysis?", "A","Sigmund Freud", "Sigmund Freud", "Carl Jung", "Ivan Pavlov", "Alfred Adler"));
                add(new QuestionModule("Who was the first American woman in space?", "B","Sally Ride", "Valentina Tereshkova", "Sally Ride", "Mae Jemison", "Judith Resnik"));
                add(new QuestionModule("Which country was ruled by Catherine the Great?", "A","Russia", "Russia", "France", "England", "Spain"));
                add(new QuestionModule("Who painted 'The School of Athens'?", "C","Raphael", "Leonardo da Vinci", "Michelangelo", "Raphael", "Donatello"));
                add(new QuestionModule("Which war resulted in the independence of the United States?", "D","American Revolutionary War", "American Civil War", "French and Indian War", "War of 1812", "American Revolutionary War"));
                add(new QuestionModule("Who composed 'The Magic Flute'?", "D","Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Johann Sebastian Bach", "Antonio Vivaldi", "Wolfgang Amadeus Mozart"));
                add(new QuestionModule("Who wrote 'The Prince'?", "B","Niccolò Machiavelli", "Thomas More", "Niccolò Machiavelli", "Francis Bacon", "John Locke"));

            }
        };
        QuestionModule.createQuestionsForSubject("201 To 210", questions);





        //------------- 211-220
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which institution adopted the ‘International Treaty to Protect the High Seas’?", "B", "UN","WEF", "UN", "NITI Aayog", "World Bank"));
                add(new QuestionModule("Which global bloc is associated with ‘USD 55 billion package to support Ukraine’?", "D", "EU","G-7", "G-20", "ASEAN", "EU"));
                add(new QuestionModule("The National Institute of Standards and Technology (NIST) is associated with which country?", "C", "USA","Russia", "Bangladesh", "USA", " Australia"));
                add(new QuestionModule("Archaeologists have unearthed a 4,000-year-old sanctuary in which country", "A", "Netherlands","Netherlands", "Greece", "Japan", "China"));
                add(new QuestionModule("Which country announced the ‘In-country’ renewable H-1B visas for India?", "B", "USA","Australia", "USA", "UK", "Germany"));
                add(new QuestionModule("Jizai Arms is developed in which country?", "B", "Japan","India", "Japan", "South Korea", "USA"));
                add(new QuestionModule("National Defence Mobilisation Offices (NDMO) are associated with which country?", "D", "China","Bangladesh", "Japan", "Russia", "China"));
                add(new QuestionModule("Which neighboring country of India recently enacted a new foreign relations law?", "B", "China","Sri Lanka", "China", "Bangladesh", "Myanmar"));
                add(new QuestionModule("France has collaborated with which country to fast-track transition to clean energy?", "A", "UAE","UAE", "Italy", "Germany", "UK"));
                add(new QuestionModule("Septimius Severus was the Emperor of which country/ region?", "D", "Rome","Greece", "Turkey", "Mongolia", "Rome"));
            }
        };
        QuestionModule.createQuestionsForSubject("211 To 220", questions);



        //------------- 221-230
        questions = new ArrayList(){
            {
                add(new QuestionModule("How many teeth does an adult human have?", "B", "32","28", "32", "30", "26"));
                add(new QuestionModule("Who invented the lightbulb?", "C", "Thomas Edison","Albert Einstein", "Nikola Tesla", "Thomas Edison", "Alexander Graham Bell"));
                add(new QuestionModule("What is the hottest planet in the solar system?", "C", "Venus","Mercury", "Mars", "Venus", "Jupiter"));
                add(new QuestionModule("Who composed the music for The Nutcracker?", "D", "Tchaikovsky","Mozart", "Beethoven", "Bach", "Tchaikovsky"));
                add(new QuestionModule("What does NASA stand for?", "B", "National Aeronautics and Space Administration ","North American Satellite Association", "National Aeronautics and Space Administration ", "National Association of Space Astronauts", "National American Space Association"));
                add(new QuestionModule("Who was the first President of the United States?", "D", "George Washington","Thomas Jefferson", "Benjamin Franklin", "Abraham Lincoln", "George Washington"));
                add(new QuestionModule("In what year was the United Nations founded?", "B", "1945","1920", "1945", "1950", "1935"));
                add(new QuestionModule("Who is the CEO of Tesla?", "D", "Elon Musk","Jeff Bezos", "Mark Zuckerberg", "Tim Cook", "Elon Musk"));
                add(new QuestionModule("What type of animal is a penguin?", "D", "Bird ","Mammal", "Reptile", "Insect", "Bird "));
                add(new QuestionModule("What is the capital city of Canada?", "A", "Ottawa","Ottawa", "Vancouver", "Toronto", "Quebec City"));
            }
        };
        QuestionModule.createQuestionsForSubject("221 To 230", questions);

        //------------- 231-240
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which emperor built the Colosseum in Rome?", "D","Vespasian", "Augustus", "Nero", "Trajan", "Vespasian"));
                add(new QuestionModule("Who discovered the laws of heredity?", "B","Gregor Mendel", "Charles Darwin", "Gregor Mendel", "Alfred Wallace", "Thomas Huxley"));
                add(new QuestionModule("Who was the first President of the United States of America?", "A","George Washington", "George Washington", "Thomas Jefferson", "John Adams", "Benjamin Franklin"));
                add(new QuestionModule("Who was the last pharaoh of ancient Egypt?", "C","Cleopatra VII", "Ramses II", "Tutankhamun", "Cleopatra VII", "Hatshepsut"));
                add(new QuestionModule("Who painted 'Girl with a Pearl Earring'?", "C","Johannes Vermeer", "Rembrandt", "Vincent van Gogh", "Johannes Vermeer", "Pablo Picasso"));
                add(new QuestionModule("Who was the first African-American President of the United States?", "D","Barack Obama", "Martin Luther King Jr.", "Malcolm X", "Jesse Jackson", "Barack Obama"));
                add(new QuestionModule("Which ancient civilization built the city of Petra?", "B","Nabataeans", "Romans", "Nabataeans", "Greeks", "Persians"));
                add(new QuestionModule("Who wrote 'The Canterbury Tales'?", "A","Geoffrey Chaucer", "William Shakespeare", "Geoffrey Chaucer", "John Milton", "Dante Alighieri"));
                add(new QuestionModule("Who invented the printing press?", "D","Johannes Gutenberg", "Leonardo da Vinci", "Isaac Newton", "Galileo Galilei", "Johannes Gutenberg"));
                add(new QuestionModule("Who painted 'The Girl with the Dragon Tattoo'?", "A","Leonardo da Vinci", "Rembrandt", "Claude Monet", "Vincent van Gogh", "Rembrandt"));

            }
        };
        QuestionModule.createQuestionsForSubject("231 To 240", questions);

        //------------- 241-250
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who discovered radium and polonium?", "A","Marie Curie", "Marie Curie", "Albert Einstein", "Ernest Rutherford", "Niels Bohr"));
                add(new QuestionModule("Who was the founder of the Jesuit order?", "D","Ignatius of Loyola", "Martin Luther", "John Calvin", "Desiderius Erasmus", "Ignatius of Loyola"));
                add(new QuestionModule("Who wrote '1984'?", "A","George Orwell", "George Orwell", "Aldous Huxley", "Ray Bradbury", "Philip K. Dick"));
                add(new QuestionModule("Who was the first woman to win a Nobel Peace Prize?", "C","Bertha von Suttner", "Mother Teresa", "Jane Addams", "Bertha von Suttner", "Rigoberta Menchú"));
                add(new QuestionModule("Which American President is associated with the New Deal?", "B","Franklin D. Roosevelt", "Herbert Hoover", "Franklin D. Roosevelt", "Harry S. Truman", "Woodrow Wilson"));
                add(new QuestionModule("Who composed 'The Nutcracker'?", "D","Pyotr Ilyich Tchaikovsky", "Ludwig van Beethoven", "Johann Sebastian Bach", "Wolfgang Amadeus Mozart", "Pyotr Ilyich Tchaikovsky"));
                add(new QuestionModule("Who was the first woman to win a Nobel Prize in Chemistry?", "B","Marie Curie", "Dorothy Crowfoot Hodgkin", "Marie Curie", "Ada Yonath", "Frances Arnold"));
                add(new QuestionModule("Who wrote 'Pride and Prejudice'?", "A","Jane Austen", "Jane Austen", "Charlotte Brontë", "Emily Brontë", "Charles Dickens"));
                add(new QuestionModule("Who discovered the law of conservation of mass?", "C","Antoine Lavoisier", "John Dalton", "Dmitri Mendeleev", "Antoine Lavoisier", "Joseph Priestley"));
                add(new QuestionModule("Who was the first woman to fly solo nonstop across the Atlantic?", "D","Amelia Earhart", "Bessie Coleman", "Harriet Quimby", "Jacqueline Cochran", "Amelia Earhart"));

            }
        };
        QuestionModule.createQuestionsForSubject("241 To 250", questions);

        //------------- 251-260
        questions = new ArrayList(){
            {
                add(new QuestionModule("What is the strongest muscle in the human body?", "D", "Jaw Muscle (Masseter)","Biceps", "Quadriceps", "Heart", "Jaw Muscle (Masseter)"));
                add(new QuestionModule("Who wrote the novel Pride and Prejudice?", "C", "Jane Austen","Charlotte Bronte", "Mary Shelley", "Jane Austen", "Emily Dickinson"));
                add(new QuestionModule("What is the fastest land animal?", "A", "Cheetah","Cheetah", "Ostrich", "Lion", "Elephant"));
                add(new QuestionModule("Who painted Starry Night?", "B", "Vincent van Gogh","Pablo Picasso", "Vincent van Gogh", "Claude Monet", "Edvard Munch"));
                add(new QuestionModule("How many bones are there in the adult human body?", "B", "206","256", "206", "216", "232"));
                add(new QuestionModule("What is the largest country in the world by area?", "D", "Canada","Canada", "United States", "Australia", "Russia "));
                add(new QuestionModule("What is the loudest animal on Earth?", "D", "Sperm Whale","Lion", "Elephant", "Blue Whale", "Sperm Whale"));
                add(new QuestionModule("What is the smallest country in the world?", "B", "Vatican City","Monaco", "Vatican City", "Malta", "San Marino"));
                add(new QuestionModule("Who was Shakespeare?", "C", "An English playwright and poet","A classical composer", "A British King", "An English playwright and poet", "A scientist"));
                add(new QuestionModule("Who discovered penicillin?", "D", "Alexander Fleming","Isaac Newton", "Thomas Edison", "Marie Curie", "Alexander Fleming"));
            }
        };
        QuestionModule.createQuestionsForSubject("251 To 260", questions);


        //------------- 261-270
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which composer is known for 'The Four Seasons'?", "A","Antonio Vivaldi", "Antonio Vivaldi", "Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Johann Sebastian Bach"));
                add(new QuestionModule("Who wrote 'The Picture of Dorian Gray'?", "D","Oscar Wilde", "Charles Dickens", "Emily Brontë", "F. Scott Fitzgerald", "Oscar Wilde"));
                add(new QuestionModule("Who invented the steam engine?", "C","James Watt", "Thomas Edison", "Nikola Tesla", "James Watt", "George Stephenson"));
                add(new QuestionModule("Who was the author of 'Jane Eyre'?", "C","Charlotte Brontë", "Emily Brontë", "Jane Austen", "Charlotte Brontë", "George Eliot"));
                add(new QuestionModule("Which queen of England is known for defeating the Spanish Armada?", "A","Queen Elizabeth I", "Queen Elizabeth I", "Queen Mary I", "Queen Victoria", "Queen Anne"));
                add(new QuestionModule("Who painted 'The Night Watch'?", "B","Rembrandt", "Vincent van Gogh", "Rembrandt", "Claude Monet", "Pablo Picasso"));
                add(new QuestionModule("Who wrote 'Oliver Twist'?", "A","Charles Dickens", "Charles Dickens", "Jane Austen", "Leo Tolstoy", "Victor Hugo"));
                add(new QuestionModule("Who was the first woman to win a Nobel Prize in Physics?", "D","Marie Curie", "Lise Meitner", "Maria Goeppert Mayer", "Donna Strickland", "Marie Curie"));
                add(new QuestionModule("Who composed 'Swan Lake'?", "D","Pyotr Ilyich Tchaikovsky", "Ludwig van Beethoven", "Johann Sebastian Bach", "Wolfgang Amadeus Mozart", "Pyotr Ilyich Tchaikovsky"));
                add(new QuestionModule("Who founded modern nursing?", "B","Florence Nightingale", "Clara Barton", "Florence Nightingale", "Mary Seacole", "Dorothea Dix"));

            }
        };
        QuestionModule.createQuestionsForSubject("261 To 270", questions);

        //------------- 271-280
        questions = new ArrayList(){
            {
                add(new QuestionModule("U.S. Development Finance Corporation (DFC) pledged USD 553 million funding for which Indian project?", "B", "Colombo West International Terminal Project","Narmada Valley Development Project", "Colombo West International Terminal Project", "Inland Development Waterways Project", "Mumbai Trans Harbour Link"));
                add(new QuestionModule("As per a recent UN report, which region was ranked as World’s Worst Region for Water Scarcity?", "C", "South Asia","Africa", "South America", "South Asia", "Europe"));
                add(new QuestionModule("Tailored Deterrence Strategy (TDS) is associated with which countries?", "B", "USA-South Korea","India-USA", "USA-South Korea", "Russia-China", "China-Pakistan"));
                add(new QuestionModule("Which is the Cambridge Dictionary’s Word of the Year for 2023?", "A", "Hallucinate","Hallucinate", "Manipulate", "Backfire", "Depression"));
                add(new QuestionModule("Which country listed Lashkar-e-Taiba as a terror outfit?", "A", "Israel","Israel", "USA", "UAE", "Iran"));
                add(new QuestionModule("Which country inaugurated the world’s largest single-site solar power plant ‘Al Dhafra’ Project?", "B", "UAE","Israel", "UAE", "Iran", "Oman"));
                add(new QuestionModule("World Health Organisation (WHO) confirmed sexual transmission of mpox, or monkeypox, in which country?", "C", "Congo","Kenya", "Russia", "Congo", "Argentina"));
                add(new QuestionModule("‘Cairns Group’, which is seen in the news, is associated with countries of which category?", "B", "Agricultural Exporting","Oil Exporting", "Agricultural Exporting", "Milk Exporting", "Meat Exporting"));
                add(new QuestionModule("Hambantota port, which was seen in the news, is in which country?", "C", "Sri Lanka","Israel", "Ukraine", "Sri Lanka", "Afghanistan"));
                add(new QuestionModule("India announced a USD 250 million line of credit to which country for modernisation of its agricultural sector?", "D", "Kenya","South Africa", "Congo", "Papua New Guinea", "Kenya"));
            }
        };
        QuestionModule.createQuestionsForSubject("271 To 280", questions);

        //------------- 281-290
        questions = new ArrayList(){
            {
                add(new QuestionModule("What are the primary colors?", "A", "Yellow, Blue, Red","Yellow, Blue, Red", "Yellow, Orange, Green", "Orange, Purple, Green", "Blue, Green, Yellow"));
                add(new QuestionModule("What does WWW stand for?", "A", "World Wide Web","World Wide Web", "World Web Warriors", "Wide World Web", "Web Wide World"));
                add(new QuestionModule("What is the capital city of Spain?", "D", "Madrid","Seville", "Barcelona", "Valencia", "Madrid"));
                add(new QuestionModule("Who directed the movie Titanic?", "B", "James Cameron","Steven Spielberg", "James Cameron", "Alfred Hitchcock", "Tim Burton"));
                add(new QuestionModule("In what year was Facebook founded?", "B", "2004","2000", "2004", "2008", "2010"));
                add(new QuestionModule("Who were the Wright Brothers?", "D", "Pioneers in aviation","Scientists who discovered electricity", "Astronauts who landed on the moon", "Soviet spies during the cold war", "Pioneers in aviation"));
                add(new QuestionModule("What is the square root of 121?","C", "11","10", "13", "11", "9" ));
                add(new QuestionModule("Who was the first man to walk on the moon?", "D", "Neil Armstrong","John Glenn", "Yuri Gagarin", "Buzz Aldrin", "Neil Armstrong"));
                add(new QuestionModule("What is the world's largest desert?", "D", "Antarctic Desert","Mojave", "Siberian Desert", "Sahara", "Antarctic Desert"));
                add(new QuestionModule("Who composed the Four Seasons?", "C", "Vivaldi","Beethoven", "Mozart", "Vivaldi ", "Handel"));
            }
        };
        QuestionModule.createQuestionsForSubject("281 To 290", questions);


        //------------- 291-300
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who wrote 'The Scarlet Letter'?", "D","Nathaniel Hawthorne", "Herman Melville", "Ralph Waldo Emerson", "Edgar Allan Poe", "Nathaniel Hawthorne"));
                add(new QuestionModule("Who discovered X-rays?", "B","Wilhelm Conrad Roentgen", "Marie Curie", "Wilhelm Conrad Roentgen", "Albert Einstein", "Niels Bohr"));
                add(new QuestionModule("Who composed 'Rhapsody in Blue'?", "A","George Gershwin", "Aaron Copland", "George Gershwin", "Leonard Bernstein", "Scott Joplin"));
                add(new QuestionModule("Who was the first American woman to win a Nobel Peace Prize?", "D","Jane Addams", "Mother Teresa", "Malala Yousafzai", "Rigoberta Menchú", "Jane Addams"));
                add(new QuestionModule("Who wrote 'Dracula'?", "C","Bram Stoker", "Mary Shelley", "H.P. Lovecraft", "Bram Stoker", "Edgar Allan Poe"));
                add(new QuestionModule("Who discovered the double helix structure of DNA?", "D","James Watson and Francis Crick", "Rosalind Franklin", "Linus Pauling", "Barbara McClintock", "James Watson and Francis Crick"));
                add(new QuestionModule("Who composed 'The Planets'?", "A","Gustav Holst", "Gustav Holst", "Richard Strauss", "Aaron Copland", "Igor Stravinsky"));
                add(new QuestionModule("Who was the first female Secretary of State of the United States?", "B","Madeleine Albright", "Madeleine Albright", "Condoleezza Rice", "Hillary Clinton", "Janet Reno"));
                add(new QuestionModule("Who wrote 'Moby-Dick'?", "B","Herman Melville", "Nathaniel Hawthorne", "Herman Melville", "Ralph Waldo Emerson", "Edgar Allan Poe"));
                add(new QuestionModule("Who was the first woman to win a Nobel Prize in Literature?", "C","Selma Lagerlöf", "Pearl S. Buck", "Gabriela Mistral", "Selma Lagerlöf", "Toni Morrison"));

            }
        };
        QuestionModule.createQuestionsForSubject("291 To 300", questions);

        //-----------------301-310
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which institution released a new guideline on non-sugar sweeteners (NSS)?", "B","WHO", "UNICEF", "WHO", "UNEP", "NITI Aayog"));
                add(new QuestionModule("Russia signed an agreement with which country to finalise the construction of Rasht-Astara Railway?", "A", "Iran","Iran", "Israel","UAE", "China"));
                add(new QuestionModule("WildCRU, a research unit within Oxford University collaborated with Panthera to conserve which animal?", "B", "African Lions","Asian Lions", "African Lions","African Elephants", "Great Indian Bustards"));
                add(new QuestionModule("The US signed a defence cooperation agreement with which country?", "B", "Papua New Guinea","Indonesia", "Papua New Guinea","Australia", "Singapore"));
                add(new QuestionModule("Clarion-Clipperton Zone’, which was seen in the news, is located in which ocean?", "B", "Pacific Ocean","Indian Ocean", "Pacific Ocean", "Atlantic Ocean" , "Arctic Ocean"));
                add(new QuestionModule("As per NASA’s spacecraft Juno, lightning in which planet is similar to that of the Earth?", "D", "Jupiter"," Mercury", "Mars", "Venus", "Jupiter"));
                add(new QuestionModule("What is Hvaldimir, which is being suspected of being a Russian spy?", "C", "Whale","Robot", "Eagle", "Whale", "Horse"));
                add(new QuestionModule("Which country leads the ‘Combined Maritime Forces’ coalition?", "B", "USA","Russia", "USA", "UK", "Germany"));
                add(new QuestionModule("Which country is set to deploy a satellite constellation called “Silent Barker”?", "D", "USA","Ukraine", "China", "Russia", "USA"));
                add(new QuestionModule("Which country has declared the UN envoy Volker Perthes to be persona non grata in that country?", "A", "Sudan","Sudan", "Germany", "Ethiopia", "South Africa"));
            }
        };
        QuestionModule.createQuestionsForSubject("301 To 310",  questions);



        //------------- 311-320
        questions = new ArrayList(){
            {
                add(new QuestionModule("What do Koalas usually eat?", "B", "Eucalyptus","Bamboo", "Eucalyptus", "Aloe Vera", "Banana"));
                add(new QuestionModule("What is the most popular bread in France?", "B", "Baguette","Brioche", "Baguette", "White bread", "Ciabatta"));
                add(new QuestionModule("What is the official currency of Japan?", "C", "Yen","Won", "Yuan", "Yen", "Dollars"));
                add(new QuestionModule("What is the phobia of thunder and rain?", "B", "Ombrophobia","Agoraphobia", "Ombrophobia", "Acrophobia", "Claustrophobia"));
                add(new QuestionModule("What does Carpe Diem mean in Latin?", "A", "Enjoy the moment","Enjoy the moment", "Have no fear", "Sorry I blew it", "Hello"));
                add(new QuestionModule("Which one of the following countries is not in Africa?", "D", "Yemen","Morocco", "Sudan", "Algeria", "Yemen"));
                add(new QuestionModule("What is considered the lung of the Earth?", "A", "Amazon rainforest","Amazon rainforest", "The Mississippi River", "The Sahara", "Everest"));
                add(new QuestionModule("Where does the Sushi come from?", "D", "Japan","China", "America", "South Korea", "Japan"));
                add(new QuestionModule("In which century the Mona Lisa was painted?", "C", "16th century","18th century", "15th century", "16th century", "14th century"));
                add(new QuestionModule("Who wrote the “Great Gatsby” novel?", "D", "Scott Fitzgerald","Leo Tolstoy", "Hemingway", "Stephen King", "Scott Fitzgerald"));
            }
        };
        QuestionModule.createQuestionsForSubject("311 To 320", questions);

        //-------------321-330
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which is the richest country in the world?", "D", "Qatar","Russia", "The USA", "The UAE", "Qatar"));
                add(new QuestionModule("Which county is the biggest grower of coffee?", "B", "Brazil","Spain", "Brazil", "India", "Ethiopia"));
                add(new QuestionModule("How many bones are in the body of an adult human?", "C", "206","330", "250", "206", "210"));
                add(new QuestionModule("When the humans use more facial muscles?", "B", "While frowning","While smiling", "While frowning", "While sleeping", "While talking"));
                add(new QuestionModule(" Who wrote “Crime and Punishment”?", "D", "Fyodor Dostoevsky","Leo Tolstoy", "Anton Chekhov", "Ivan Turgenev", "Fyodor Dostoevsky"));
                add(new QuestionModule("In what year was the United Nations (UN) founded?", "A", "1945","1945", "1919", "1956", "1961"));
                add(new QuestionModule("Which city is called the “City of Winds”?", "A", "Chicago","Chicago", "Seattle", "Washington", "Veliky Novgorod"));
                add(new QuestionModule("What animal is a symbol of peace and neutrality?", "D", "White crane","Polar bear", "White tiger", "White lion", "White crane"));
                add(new QuestionModule("What element is the main constituent of diamonds?", "C", "Carbon","Oxygen", "Silver", "Carbon", "Gold"));
                add(new QuestionModule("In what year was Ferdinand Magellan's first successful circumnavigation of the world?", "B", "1520","1492", "1520", "1588", "1620"));
            }
        };
        QuestionModule.createQuestionsForSubject("321 To 330", questions);

        //------------- 331-340
        questions = new ArrayList(){
            {
                add(new QuestionModule("What is the longest river in the world?", "B", "Nile River","Amazon River", "Nile River", "Yangtze River", "Mississippi River"));
                add(new QuestionModule("Who are the two main characters in Romeo and Juliet?", "C", "Romeo and Juliet","Hamlet and Ophelia", "Macbeth and Lady Macbeth", "Romeo and Juliet", "Othello and Desdemona"));
                add(new QuestionModule("In what country are the Pyramids of Giza located?", "C", "Egypt","Iran", "IraqIraq", "Egypt", "Saudi Arabia"));
                add(new QuestionModule("What is a group of crows called?", "D", "A murder","A pack", "A flock", "A caw", "A murder"));
                add(new QuestionModule("What does the prefix kilo mean in the metric system?", "B", "Thousand","Hundred", "Thousand", "Ten", "Million"));
                add(new QuestionModule("Who directed the 2019 film Parasite?", "C", "Bong Joon-ho","Quentin Tarantino", "Martin Scorsese", "Bong Joon-ho", "Christopher Nolan"));
                add(new QuestionModule("What color are emeralds?", "A", "Green","Green", "Blue", "Red", "Purple"));
                add(new QuestionModule("Where do kangaroos originate from?", "D", "Australia","Africa", "South America", "North America", "Australia"));
                add(new QuestionModule("Who sang the 1980s hit Like a Virgin?", "A", "Madonna","Madonna", "Cher", "Cyndi Lauper", "Tina Turner"));
                add(new QuestionModule("What country does sushi originate from?", "D", "Japan","China", "Korea", "Thailand", "Japan"));
            }
        };
        QuestionModule.createQuestionsForSubject("331 To 340", questions);

        //------------- 341-350
        questions = new ArrayList(){
            {
                add(new QuestionModule("What does DNA stand for?", "B", "Deoxyribonucleic Acid","Deoxyribose Nitrogen Acid", "Deoxyribonucleic Acid", "Deoxyribonicle Acid", "Deoxidized Nucleic Acid"));
                add(new QuestionModule("What word describes a word that spells the same backward and forwards?", "C", "Palindrome","Homonym", "Synonym", "Palindrome", "Antonym"));
                add(new QuestionModule("What is the primary ingredient in guacamole?", "C", "Avocado","Lime", "Tomatoes", "Avocado", "Peppers"));
                add(new QuestionModule("What year did World War II end?", "B", "1945","1941", "1945", "1948", "1950"));
                add(new QuestionModule("Who played the Joker in the 2019 film?", "D", "Joaquin Phoenix","Jack Nicholson", "Heath Ledger", "Jared Leto", "Joaquin Phoenix"));
                add(new QuestionModule("Whose face is on a $5 bill (the US)?", "C", "Abraham Lincoln","George Washington", "Benjamin Franklin", "Abraham Lincoln", "Thomas Jefferson"));
                add(new QuestionModule("Who wrote the novel To Kill a Mockingbird?", "C", "Harper Lee","Mark Twain", "Ernest Hemingway", "Harper Lee", "Stephen King"));
                add(new QuestionModule("Which planet has the most moons?", "D", "Jupiter","Mars", "Venus", "Saturn", "Jupiter"));
                add(new QuestionModule("In what city is the United Nations headquartered?", "B", "New York, USA","Geneva, Switzerland", "New York, USA", "Paris, France", "Vienna, Austria"));
                add(new QuestionModule("Who composed the opera Madame Butterfly?", "D", "Giacomo Puccini","Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Johann Sebastian Bach", "Giacomo Puccini"));
               }
        };
        QuestionModule.createQuestionsForSubject("341 To 350", questions);

        //-------------351-360
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which river is the second longest in the world?", "B", "Yangtze","Amazon", "Yangtze", "Nile", "Mississippi"));
                add(new QuestionModule("What year was the first man sent to space?", "B", "1961","1957", "1961", "1969", "1975"));
                add(new QuestionModule("Which sea is considered the most salty on Earth?", "C", "Dead Sea","Red Sea", "Mediterranean Sea", "Dead Sea", "Black Sea"));
                add(new QuestionModule("Which organ in the human body is responsible for the secretion of bile?", "A", "Liver","Liver", "Kidneys", "Spleen", "Stomach"));
                add(new QuestionModule("Which planet in the solar system is known as the “Morning Star” or “Evening Star”?", "D", "Venus","Mars", "Mercury", "Jupiter", "Venus"));
                add(new QuestionModule("What chemical element is designated as “Cu”?", "B", "Copper","Zinc", "Copper", "Cobalt", "Kurtz"));
                add(new QuestionModule("In what year did the French Revolution take place?", "A", "1789","1789", "1812", "1905", "1793"));
                add(new QuestionModule("What ocean is between Africa and Australia?", "B", "Indian","Pacific", "Indian", "Atlantic", "Arctic"));
                add(new QuestionModule("Which chemical element makes up most of the atmosphere of Mars?", "A", "Carbon dioxide","Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"));
                add(new QuestionModule(" What chemical element makes up most of the atmosphere of Titan?", "D", "Methane","Carbon dioxide", "Hydrogen", "Oxygen", "Methane"));
            }
        };
        QuestionModule.createQuestionsForSubject("351 To 360", questions);

        //------------- 361-370
        questions = new ArrayList(){
            {
                add(new QuestionModule("In what year did the discovery of Antarctica occur?", "C", "1820","1500", "1778", "1820", "1892"));
                add(new QuestionModule("In what year was the International Red Cross and Red Guard Organization (Red Cross) founded?", "A", "1863","1863", "1901", "1945", "1980"));
                add(new QuestionModule("What instrument is depicted in Leonardo da Vinci's famous painting “The Last Supper”?", "D", "Lute","Tablets", "Guitar", "Harp", "Lute"));
                add(new QuestionModule("In which country did the Chernobyl nuclear disaster take place?", "B", "Ukraine","Russia", "Ukraine", "Belarus", "Lithuania"));
                add(new QuestionModule("What ocean lies between North America and Eurasia?", "A", "Atlantic","Atlantic", "Quiet", "Indian", "Arctic"));
                add(new QuestionModule("In what year was the manga Karta signed?", "D", "1215","1066", "1492", "1689", "1215"));
                add(new QuestionModule("What chemical element is used to cool and freeze food?", "C", "Nitrogen","Sodium", "Hydrogen", "Nitrogen", "Oxygen"));
                add(new QuestionModule("Which country is famous for its pyramids and the Sphinx?", "D", "Egypt","India", "China", "Mexico", "Egypt"));
                add(new QuestionModule("What is the name of the science that studies the past of life on Earth?", "A", "Palaeontology","Palaeontology", "Astronomy", "Anthropology", "Geology"));
                add(new QuestionModule("What chemical element forms the basis of the haemoglobin molecule responsible for the transport of oxygen in the blood?", "B", "Iron","Nitrogen", "Iron", "Oxygen", "Silver"));
            }
        };
        QuestionModule.createQuestionsForSubject("361 To 370", questions);

        //------------- 371-380
        questions = new ArrayList(){
            {
                add(new QuestionModule("How many strings does a standard guitar have?", "C", "Six","Four", "Five", "Six", "Seven"));
                add(new QuestionModule("What is the capital city of Italy?", "D", "Rome","Milan", "Venice", "Florence", "Rome"));
                add(new QuestionModule("Who is the hero of the Indiana Jones series?", "B", "Harrison Ford","Tom Cruise", "Harrison Ford", "Chris Evans", "Robert Downey Jr."));
                add(new QuestionModule("What kind of animal is Shrek?", "C", "Ogre","Donkey", "Dragon", "Ogre", "Giant"));
                add(new QuestionModule("What is the biggest animal in the world?", "B", "Blue Whale","African Elephant", "Blue Whale", "Saltwater Crocodile", "White Rhinoceros"));
                add(new QuestionModule("In what country are the 2024 Summer Olympics held?", "D", "Paris, France","Tokyo, Japan", "Rio de Janeiro, Brazil", "London, United Kingdom", "Paris, France"));
                add(new QuestionModule("What language is spoken in Argentina?", "C", "Spanish","English", "Portuguese", "Spanish", "French"));
                add(new QuestionModule("Who painted The Scream?", "C", "Edvard Munch","Pablo Picasso", "Vincent van Gogh", "Edvard Munch", "Claude Monet"));
                add(new QuestionModule("What chemical element has the symbol Na?", "D", "Nitrogen","Oxygen", "Argon", "Carbon Dioxide", "Nitrogen"));
                add(new QuestionModule("Who is the author of The Great Gatsby?", "A", "F. Scott Fitzgerald","F. Scott Fitzgerald", "J.D. Salinger", "Ernest Hemingway", "Mark Twain"));
            }
        };
        QuestionModule.createQuestionsForSubject("371 To 380", questions);

        //-------------381-390
        questions = new ArrayList(){
            {
                add(new QuestionModule("In what year was the League of Nations founded?", "A", "1919","1919", "1923", "1939", "1945"));
                add(new QuestionModule("Which planet in the solar system has the most diverse climate and atmosphere?", "B", "Earth","Mercury", "Earth", "Mars", "Venus"));
                add(new QuestionModule("In what year did the Berlin Wall fall?", "B", "1989","1961", "1989", "1991", "2000"));
                add(new QuestionModule("Which sea lies between Asia and Africa?", "C", "Mediterranean","Caspian", "Red", "Mediterranean", "Dead"));
                add(new QuestionModule("Which organ in the human body is responsible for secreting insulin?", "C", "Pancreas","Liver", "Kidneys", "Pancreas", "Stomach"));
                add(new QuestionModule("What year did the Great Depression start?", "A", "1929","1929", "1914", "1939", "1945"));
                add(new QuestionModule("What chemical element is used in the manufacture of glass?", "D", "Silicone","Silver", "Sodium", "Oxygen", "Silicone"));
                add(new QuestionModule("Which instrument is depicted in Edvard Munch's famous painting “The Scream”?", "B", "Cello","Flute", "Cello", "Clarinet", "Pictures"));
                add(new QuestionModule("Which planet in the solar system is known as the “Planet of Love”?", "A", "Venus","Venus", "Mars", "Earth", "Jupiter"));
                add(new QuestionModule("What chemical element is the main component of carbon dioxide (CO2)?", "D", "Carbon","Oxygen", "Hydrogen", "Nitrogen", "Carbon"));
            }
        };
        QuestionModule.createQuestionsForSubject("381 To 390", questions);


        //-------------391-400
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who is the creator of the TV show Game of Thrones?", "A", "George R.R. Martin","George R.R. Martin", "J.R.R. Tolkien", "David Benioff", "D.B. Weiss"));
                add(new QuestionModule("How many stars are there on the flag of China?", "C", "5","1", "3", "5", "7"));
                add(new QuestionModule("What type of car is the mystery machine in the Scooby-Doo series?", "C", "Van","Pickup Truck", "Sports Car", "Van", "Classic Car"));
                add(new QuestionModule("Who first said, I think, therefore I am?", "D", "Descartes ","Plato", "Aristotle", "Socrates", "Descartes"));
                add(new QuestionModule("The Great Barrier Reef is off the coast of which country?", "B", "Australia","New Zealand", "Australia", "Chile", "South Africa"));
                add(new QuestionModule("How many constellations are recognized by the International Astronomical Union?", "A", "88","88", "100", "92", "76"));
                add(new QuestionModule("How many legs does a spider usually have?", "C", "Eight","Four", "Six", "Eight", "Ten"));
                add(new QuestionModule("What is the main ingredient in chocolate?", "D", "Cocoa Beans","Sugar", "Milk", "Wheat", "Cocoa Beans"));
                add(new QuestionModule("What year did women get the right to vote in the United States?", "A", "1920","1920", "1930", "1940", "1950"));
                add(new QuestionModule("Who is the most decorated Olympian of all time?", "C", "Michael Phelps","Usain Bolt", "Simone Biles", "Michael Phelps", "Carl Lewis"));
            }
        };
        QuestionModule.createQuestionsForSubject("391 To 400", questions);



        //------------- 401-410
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("401 To 410", questions);*/

        //------------- 411-420
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("411 To 420", questions);*/

        //-------------421-430
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("421 To 430", questions);*/

        //------------- 431-440
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("431 To 440", questions);*/

        //------------- 441-450
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("441 To 450", questions);*/

        //-------------451-460
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("451 To 460", questions);*/

        //------------- 461-470
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("461 To 470", questions);*/

        //------------- 471-480
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("471 To 480", questions);*/

        //-------------481-490
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("481 To 490", questions);*/


        //-------------491-500
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("491 To 500", questions);*/





















        //------------- Subject 2
/*        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("11 To 20", questions);*/


    }
}
