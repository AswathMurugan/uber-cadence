package example.temporal.signalProto;

import example.temporal.signalProto.processor.ProcessorActivityImpl;
import example.temporal.signalProto.processor.ProcessorWorkFlowImpl;
import example.temporal.signalProto.senderWorkFlow.senderA.AWorkFlowImpl;

import example.temporal.signalProto.senderWorkFlow.senderA.AactivityImpl;
import example.temporal.signalProto.senderWorkFlow.senderB.BWorkFlowImpl;
import example.temporal.signalProto.senderWorkFlow.senderC.CWorkFlow;
import example.temporal.signalProto.senderWorkFlow.senderC.CWorkFlowImpl;
import example.temporal.signalProto.senderWorkFlow.senderD.DWorkflow;
import example.temporal.signalProto.senderWorkFlow.senderD.DWorkflowImpl;
import example.temporal.signalProto.senderWorkFlow.senderD.DactivityImpl;
import io.temporal.api.workflowservice.v1.WorkflowServiceGrpc;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class SignalProtoWorker {
    
    public static final String TASK_QUEUE = "Signal";

    public static WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static WorkflowClient client = WorkflowClient.newInstance(service);

    public static void main(String[] args) {
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(
                AWorkFlowImpl.class,
                BWorkFlowImpl.class,
                CWorkFlowImpl.class,
                DWorkflowImpl.class);
        worker.registerActivitiesImplementations(new AactivityImpl(),
                new DactivityImpl());
        factory.start();
        System.out.println("Started the worker task queue name: " + TASK_QUEUE);
    }
}
