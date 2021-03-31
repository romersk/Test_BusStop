package by.duallab.busstop.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import by.duallab.busstop.bus.Bus;

public class FilesTest {
	private File input;
	private File output;

	public FilesTest(String directory) {
		this.input = new File(directory);

		this.output = new File(this.input.getParent(), "output.txt");
		try {
			boolean created = this.output.createNewFile();

		} catch (IOException ex) {

			System.out.println("File hasn't been created");
		}
	}

	private void outputData(ArrayList<Bus> list) throws IOException {
		FileWriter writer = new FileWriter(output.getAbsolutePath());
		for (Bus obj : list) {

			if (obj.getNameService().compareTo("Posh")==0)
			{
				writer.write(obj.getNameService() + " " + obj.getStartTime() + " " +
						obj.getEndTime() + " " + System.getProperty("line.separator"));
			}
		}
		
		writer.write(System.getProperty("line.separator"));
		
		for (Bus obj : list) {

			if (obj.getNameService().compareTo("Grotty")==0)
			{
				writer.write(obj.getNameService() + " " + obj.getStartTime() + " " +
						obj.getEndTime() + " " + System.getProperty("line.separator"));
			}
		}
		
		writer.close();
	}

	public void solveTask() throws IOException {
		ArrayList<Bus> list = new ArrayList<Bus>();

		try {
			Scanner scanFile = new Scanner(this.input);

			while (scanFile.hasNextLine()) {
				String lineStr = scanFile.nextLine();

				Bus serviceBus = new Bus();
				serviceBus.parseStringToObject(lineStr);

				if (serviceBus.checkOneHour()) {
					boolean toAdd = true;
					int index = 0;
					for (Bus obj : list) {
						if (obj.isSpecificObj(serviceBus)) {
							list.set(index, serviceBus);
							toAdd = false;
						} else if (obj.isSpecificBus(serviceBus))
						{
							toAdd = false;
						}
						index++;
					}

					if (toAdd)
						list.add(serviceBus);
				}

			}
			scanFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Collections.sort(list);

		this.outputData(list);

	}

}
