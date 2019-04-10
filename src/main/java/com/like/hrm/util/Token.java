package com.like.hrm.util;

public class Token<E> {
	public final String sequence;
	public final E kind;

	public Token(final String sequence, final E kind) {
		this.sequence = sequence;
        this.kind = kind;
    }

    @Override
    public String toString() { return kind + "'" + sequence + "'"; }
    
}
