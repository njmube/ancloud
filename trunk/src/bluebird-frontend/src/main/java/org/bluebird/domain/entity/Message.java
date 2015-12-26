package org.bluebird.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Message")
@XmlRootElement
public class Message implements Serializable
{
	private static final long serialVersionUID = -8644123651815590065L;

	public Message() {
	}
}
