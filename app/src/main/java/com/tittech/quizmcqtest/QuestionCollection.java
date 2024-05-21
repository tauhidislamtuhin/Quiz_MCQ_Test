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


/*              add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
                add(new QuestionModule("বাংলাদেশের", "D", "মেধনা","পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));*/


        //------------- 1-10
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
        QuestionModule.createQuestionsForSubject("1 To 10",  questions);



        //------------- 11-20
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
        QuestionModule.createQuestionsForSubject("11 To 20", questions);


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
                add(new QuestionModule("To scale Mount Everest, mountaineers need to go to?", "C", "Nepal","Afghanistan", "Bhutan", "ব্রহ্মপুত্র", "Myanmar"));
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
                add(new QuestionModule("What writing system did the Mayans develop?", "C", "Maya hieroglyphs","Aztec script", "Incan quipus", "ব্রহ্মপুত্র", "Zapotec script"));
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
                add(new QuestionModule("Which country released the draft regulations governing facial recognition technology usage?", "B", "মেধনা","India", "China", "UK", "USA"));
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
                add(new QuestionModule("Which of the following seas is the world's smallest and shallowest?", "D", "মেধনা","Arctic Ocean ", "Baltic Sea", "Mediterranean Sea", "Sea of Azov "));
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
        QuestionModule.createQuestionsForSubject("181 To 190", questions);*/


        //------------- 191-200
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
        QuestionModule.createQuestionsForSubject("191 To 200", questions);*/

        //------------- 201-210
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
        QuestionModule.createQuestionsForSubject("201 To 210", questions);*/














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
