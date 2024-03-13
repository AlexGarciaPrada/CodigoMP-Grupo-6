package combate2000lasecuela;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

public class Challengemanager extends AbstractManager<Challenge> {

    public Challengemanager() {
        this.setElements(new Queue<Challenge>(){

            // METODOS QUE IMPLEMENTA LA COLA
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Challenge> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Challenge challenge) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Challenge> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public boolean offer(Challenge challenge) {
                return false;
            }

            @Override
            public Challenge remove() {
                return null;
            }

            @Override
            public Challenge poll() {
                return null;
            }

            @Override
            public Challenge element() {
                return null;
            }

            @Override
            public Challenge peek() {
                return null;
            }
        });
    }

    //public Challenge deleteChallenge(Challenge challengeId){return elements.remove(challengeId);}
}
