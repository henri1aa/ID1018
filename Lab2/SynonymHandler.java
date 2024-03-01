// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
                     // IOException
import java.util.*;  // LinkedList

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        LinkedList<String> synonymLines = new LinkedList<>();
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			synonymLines.add(synonymLine);
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[synonymLines.size()];
		synonymLines.toArray(synonymData);

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word)
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word)
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData,
	    String word)
	{
		// add code here
        // En ny vektor skapas med en rad mindre 
        String[] synData = new String[synonymData.length - 1];
        int j = synonymLineIndex(synonymData, word);
        //alla strängar som uppfyller kravet kopieras in
        for(int i = 0; i < j; i++)
        {
            synData[i] = synonymData[i];
        }
        //när plats j hittas hoppar vi över den och nästa plats kopieras in till nästa rad i synData
        for(int i = j + 1; i < synonymData.length; i++)
        {
            synData[i - 1] = synonymData[i];
        }
        return synData; 
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym)
	{
        // add code here
        // Synonymdatasträng hittas för orden
        int index = synonymLineIndex(synonymData, word);
        synonymData[index] = synonymData[index] + " , " + synonym;
    }
	

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
	public static void removeSynonym (String[] synonymData,
	    String word, String synonym)
	{
        // add code here
        //vi deklarerar en variabel till den raden som ordet i fråga är på
        int lineNumber = synonymLineIndex(synonymData, word);
        synonymData[lineNumber] = synonymData[lineNumber].replaceAll(synonym + ", ",""); 
    }    
    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        // add code here
        // Byta plats på synonymerna till rätt ordning
        //vi börjar på i=0, när alla j har jämförts med i=0 stegar i upp ett steg till 1
        for(int i = 0; i < strings.length; i++)
        {
            //j är alltid objektet efter, i första fallet 1, i och j jämförs
            for(int j = i + 1; j < strings.length; j++)
            {
                //om j>i sker platsbyte med hjälp av temporära platsen e
                if((strings[i].compareTo(strings[j])) > 0)
                {
                    String e = strings[i];
                    strings[i] = strings[j];
                    strings[j] = e;
                }
            }
        }    
    }

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    // variabel t som i totala
    private static String sortSynonymLine (String synonymLine)
    {
	    // add code here
        //string s är splittning mellan ordet och dess synonymer
        //string words är splittning mellan synonymerna så alla blir egna element
        String[] s = synonymLine.split("\\|");
        String[] words = s[1].split( ",", 0);

        //for loopen tar bort mellanrum, "trimmar". det skickas upp till sortIgnoreCase
        for(int i = 0; i < words.length; i++)
            words[i] = words[i].trim();

        sortIgnoreCase(words);
        //här sätts string s och word ihop igen till en ny string t
        String t = s[0] + " | " + words[0];
            for(int j = 1; j < words.length; j++)
                t += " , " + words[j];

        return t;
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        // add code here
        //kallar på metoden som sorterar orden och får tillbaka nya värdet som skrivs ut
        sortIgnoreCase(synonymData);

        //kallar på metoden som sorterar synonymerna 
        for(int i = 0; i < synonymData.length; i++)
            synonymData[i] = sortSynonymLine(synonymData[i]);
	}
}