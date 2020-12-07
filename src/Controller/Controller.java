package Controller;

import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Values.IValue;
import Model.Values.ReferenceValue;
import Repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private StringBuilder stringAllSteps;
    private ExecutorService executor;


    public Controller(IRepository repository) {
        this.repository = repository;
        this.stringAllSteps = new StringBuilder();
    }

    public void addProgram(ProgramState programState) {
        this.repository.addProgramState(programState);
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStateList) {
        return programStateList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void allSteps() throws MyException{
        this.executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = this.removeCompletedPrograms(this.repository.getProgramStateList());

        while(programStateList.size() > 0) {
            ProgramState programState = programStateList.get(0);
            programState.getHeapTable().setContent(
                    this.garbageCollector(
                            this.getAllSymbolTabelAddresses(programStateList.stream().map(program -> program.getSymbolTabel().getContent().values()).collect(Collectors.toList())),
                            this.getAllHeapTabelAddresses(programState.getHeapTable().getContent().values()),
                            programState.getHeapTable().getContent()
                    )
            );
            this.oneStepForAllPrograms(programStateList);
            programStateList = this.removeCompletedPrograms(this.repository.getProgramStateList());
        }
        this.executor.shutdownNow();
        this.repository.setProgramStateList(programStateList);
    }

    public void oneStepForAllPrograms(List<ProgramState> programStateList) {
        programStateList.forEach(program -> this.repository.logProgramStateExecution(program));

        List<Callable<ProgramState>> callList = programStateList.stream()
                .map((ProgramState program) -> (Callable<ProgramState>)(program::oneStep))
                .collect(Collectors.toList());

        try {
            List<ProgramState> newProgramStateList = this.executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }
                        catch(Exception exception) {
                            System.out.println(exception.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            programStateList.addAll(newProgramStateList);
            programStateList.forEach(program -> this.repository.logProgramStateExecution(program));
            this.repository.setProgramStateList(programStateList);

        }
        catch(InterruptedException | MyException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private Map<Integer, IValue> garbageCollector(List<Integer> symbolTabelAddresses, List<Integer> heapTableAddresses, Map<Integer, IValue> heapTable) {
        return heapTable.entrySet().stream()
                .filter(entry -> symbolTabelAddresses.contains(entry.getKey()) || heapTableAddresses.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAllSymbolTabelAddresses(List<Collection<IValue>> symbolTabelsList) {
        List<Integer> addresses = new ArrayList<>();
        symbolTabelsList.forEach(symbolTabel -> symbolTabel.stream()
                .filter(variable -> variable instanceof ReferenceValue)
                .map(variable -> ((ReferenceValue) variable).getHeapAddress())
                .forEach(addresses::add));
        return addresses;
    }

    private List<Integer> getAllHeapTabelAddresses(Collection<IValue> heapTabel) {
        return heapTabel.stream()
                .filter(variable -> variable instanceof ReferenceValue)
                .map(variable -> ((ReferenceValue) variable).getHeapAddress())
                .collect(Collectors.toList());
    }
}
