package by.duallab.busstop.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import by.duallab.busstop.bus.Bus;
import by.duallab.busstop.sort.SortByArrivial;
import by.duallab.busstop.sort.SortByDeparture;

public class FilesTest {
	private File input;
	private File output;

	public FilesTest() {
		this.input = null;
		this.output = null;
	}

	public FilesTest(String directory) {
		try {

			this.input = new File(directory);
			this.output = new File(this.input.getParent(), "output.txt");

			@SuppressWarnings("unused")
			boolean created = this.output.createNewFile();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private void outputData(ArrayList<Bus> list) throws IOException {
		FileWriter writer = new FileWriter(output.getAbsolutePath());
		for (Bus obj : list) {

			if (obj.getNameService().compareTo("Posh") == 0) // Output Posh
			{
				writer.write(obj.getNameService() + " " + obj.getStartTime() + " " + obj.getEndTime() + " "
						+ System.getProperty("line.separator"));
			}
		}

		writer.write(System.getProperty("line.separator"));

		for (Bus obj : list) {

			if (obj.getNameService().compareTo("Grotty") == 0) // Output Grotty
			{
				writer.write(obj.getNameService() + " " + obj.getStartTime() + " " + obj.getEndTime() + " "
						+ System.getProperty("line.separator"));
			}
		}

		writer.close();
	}

	public void solveTask() throws IOException {
		ArrayList<Bus> list = new ArrayList<Bus>();

		Scanner scanFile = null;

		try {
			scanFile = new Scanner(this.input);

			while (scanFile.hasNextLine()) {
				String lineStr = scanFile.nextLine();

				Bus serviceBus = new Bus();
				serviceBus.parseStringToObject(lineStr);

				if (serviceBus.checkOneHour()) {
					
					int index = 0;
					for (Bus object: list)
					{
						if (object.compareTo(serviceBus)!=0)
						{
							index++;
						} else if (object.getNameService().compareTo(serviceBus.getNameService()) != 0)
						{
							index++;
						}
					}
					
					if (index == list.size())
						list.add(serviceBus);
					
				}
				
				
			}
			
			for (int i = 0; i < list.size()-1; ++i)
			{
				if (list.get(i).compareTo(list.get(i+1)) == 0 && list.get(i).getNameService().compareTo("Posh")==0)
				{
					list.remove(i+1);
				}
			}
			
			Collections.sort(list, new SortByArrivial());
			
			
			for (int i = 0; i < list.size()-1; ++i)
			{
				if (list.get(i).SpecificObj(list.get(i+1)))
				{
					list.remove(i);
					i--;
				}
				
			}
			
			scanFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanFile != null)
				scanFile.close();
		}

		Collections.sort(list, new SortByDeparture());

		this.outputData(list);

	}

}
