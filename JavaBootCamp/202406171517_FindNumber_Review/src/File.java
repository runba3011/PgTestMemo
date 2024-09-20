import java.util.ArrayDeque;
import java.util.Deque;

public class File {
  static Deque<Integer> A = new ArrayDeque<>();
  static Deque<Integer> B = new ArrayDeque<>();
  static Deque<Integer> C = new ArrayDeque<>();

  void f() {
    if (A.isEmpty()) {
      // nothing
    } else {
      C.push(A.pop());
      f();
      B.push(C.pop());
    }
  }
}