package cem.ttt;

public enum PlayerType {
    X {
        @Override
        public int value() {
            return 1;
        }
    },

    O {
        @Override
        public int value() {
            return 2;
        }
    };

    public abstract int value();
}
