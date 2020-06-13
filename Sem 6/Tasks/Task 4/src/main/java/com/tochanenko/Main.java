package com.tochanenko;

import mpi.MPI;

import java.util.Random;

public class Main {
    private static final int BORDER = 128;

    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        if (rank == 0) {
            int[] message = {new Random().nextInt(BORDER)};
            System.out.println(rank + " generated new message: " + message[0]);
            System.out.println("Sending [" + message[0] + "] to " + 1);
            MPI.COMM_WORLD.Send(message, 0, 1, MPI.INT, 1, 1);
            MPI.COMM_WORLD.Recv(message, 0, 1, MPI.INT, size - 1, 1);
            System.out.println("Received [" + message[0] + "]. Loop completed.");

        } else {
            int[] message = {-1};
            MPI.COMM_WORLD.Recv(message, 0, 1, MPI.INT, rank - 1, 1);
            if (message[0] == -1) {
                System.out.println("Message was not received at " + rank);
            } else {
                System.out.println("Received [" + message[0] + "] from " + (rank - 1) + " at " + rank);
                System.out.println("Sending [" + message[0] + "] to " + ((rank + 1 == size) ? 0 : rank + 1));
                MPI.COMM_WORLD.Send(message, 0, 1, MPI.INT, (rank + 1 == size) ? 0 : rank + 1, 1);
            }
        }
        MPI.Finalize();
    }
}
