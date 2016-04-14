package org.bluebird.fw.core.message;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.bluebird.fw.core.message.ResultMessages.ResultMessageType;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.springframework.web.servlet.tags.form.TagWriter;
import org.springframework.web.util.HtmlUtils;

public class MessagesTag extends RequestContextAwareTag {
	private static final long serialVersionUID = 1L;
	protected static final String DEFAULT_PANEL_CLASS_NAME = "alert";
	protected static final String DEFAULT_PANEL_TYPE_CLASS_PREFIX = "alert-";
	protected static final String DEFAULT_PANEL_ELEMENT = "div";
	protected static final String DEFAULT_OUTER_ELEMENT = "ul";
	protected static final String DEFAULT_INNER_ELEMENT = "li";
	private String name;
	private String panelClassName;
	private String panelTypeClassPrefix;
	private String panelElement;
	private String outerElement;
	private String innerElement;
	private String messagesType;
	private boolean disableHtmlEscape;

	public MessagesTag() {
		this.name = ResultMessages.DEFAULT_MESSAGES_ATTRIBUTE_NAME;

		this.panelClassName = "alert";

		this.panelTypeClassPrefix = "alert-";

		this.panelElement = "div";

		this.outerElement = "ul";

		this.innerElement = "li";

		this.messagesType = null;
	}

	TagWriter createTagWriter() {
		TagWriter tagWriter = new TagWriter(this.pageContext);
		return tagWriter;
	}

	protected int doStartTagInternal() throws JspException {
		if ((!(StringUtils.hasText(this.panelElement)))
				&& (!(StringUtils.hasText(this.outerElement)))
				&& (!(StringUtils.hasText(this.innerElement)))) {
			throw new JspTagException(
					"At least one out of panelElement, outerElement, innerElement should be set");
		}

		TagWriter tagWriter = createTagWriter();

		Object messages = this.pageContext.findAttribute(this.name);

		if (messages != null) {
			if (StringUtils.hasText(this.panelElement)) {
				tagWriter.startTag(this.panelElement);

				StringBuilder className = new StringBuilder(this.panelClassName);
				String type = getType(messages);

				if ((this.panelTypeClassPrefix != null)
						&& (StringUtils.hasText(type))) {
					if (StringUtils.hasLength(className)) {
						className.append(" ");
					}
					className.append(this.panelTypeClassPrefix);
				}
				className.append(type);

				if (StringUtils.hasText(className)) {
					tagWriter.writeAttribute("class", className.toString());
				}

			}

			if (StringUtils.hasText(this.outerElement)) {
				tagWriter.startTag(this.outerElement);
			}

			writeMessages(tagWriter, messages);

			if (StringUtils.hasText(this.outerElement)) {
				tagWriter.endTag();
			}

			if (StringUtils.hasText(this.panelElement)) {
				tagWriter.endTag();
			}
		}

		return 1;
	}

	protected void writeMessages(TagWriter tagWriter, Object messages)
			throws JspException {
		Class<?> clazz = messages.getClass();
		if (Iterable.class.isAssignableFrom(clazz)) {
			Iterable<?> col = (Iterable<?>) messages;
			for (Object message : col) {
				writeMessage(tagWriter, message);
			}
		} else if (clazz.isArray()) {
			Class<?> type = clazz.getComponentType();
			if (Object.class.isAssignableFrom(type)) {
				Object[] arr = (Object[]) messages;
				for (Object message : arr) {
					writeMessage(tagWriter, message);
				}
			} else {
				int len = Array.getLength(messages);
				for (int i = 0; i < len; i++) {
					Object message = Array.get(messages, i);
					writeMessage(tagWriter, message);
				}
			}
		} else {
			writeMessage(tagWriter, messages);
		}
	}

	protected void writeMessage(TagWriter tagWriter, Object message)
			throws JspException {
		if (message != null) {
			if (StringUtils.hasText(this.innerElement)) {
				tagWriter.startTag(this.innerElement);
			}
			if (this.disableHtmlEscape)
				tagWriter.appendValue(getText(message));
			else {
				tagWriter.appendValue(HtmlUtils.htmlEscape(getText(message)));
			}

			if (StringUtils.hasText(this.innerElement))
				tagWriter.endTag();
		}
	}

	private String getText(Object message) {
		String text = null;

		if (message instanceof String) {
			text = (String) message;
		} else if (message instanceof ResultMessage) {
			ResultMessage resultMessage = (ResultMessage) message;
			text = getText(resultMessage);
		} else if (message instanceof Throwable) {
			Throwable throwable = (Throwable) message;
			text = throwable.getMessage();
		} else {
			text = getTextInOtherCase(message);
		}
		return text;
	}

	private String getText(ResultMessage resultMessage) {
		Locale locale = getRequestContext().getLocale();
		MessageSource messageSource = getRequestContext().getMessageSource();
		String text = ResultMessageUtils.resolveMessage(resultMessage,
				messageSource, locale);

		return text;
	}

	protected String getTextInOtherCase(Object message) {
		return message.toString();
	}

	private String getType(Object messages) {
		String type = "";
		if (this.messagesType != null) {
			type = this.messagesType;
		} else if (messages instanceof ResultMessages) {
			ResultMessages resultMessages = (ResultMessages) messages;
			ResultMessageType iResultMessageType = resultMessages.getType();
			type = iResultMessageType.getType();
		}
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPanelClassName(String panelClassName) {
		this.panelClassName = panelClassName;
	}

	public void setPanelTypeClassPrefix(String panelTypeClassPrefix) {
		this.panelTypeClassPrefix = panelTypeClassPrefix;
	}

	public void setPanelElement(String panelElement) {
		this.panelElement = panelElement;
	}

	public void setOuterElement(String outerElement) {
		this.outerElement = outerElement;
	}

	public void setInnerElement(String innerElement) {
		this.innerElement = innerElement;
	}

	public void setMessagesType(String messagesType) {
		this.messagesType = messagesType;
	}

	public void setDisableHtmlEscape(String disableHtmlEscape)
			throws JspException {
		this.disableHtmlEscape = toBoolean(disableHtmlEscape,false, "disableHtmlEscape");
	}
	
	public static boolean toBoolean(String attributeValue,boolean defaultValue, String attributeName) throws JspTagException {
		if (StringUtils.hasText(attributeValue)) {
			if ((attributeValue.equalsIgnoreCase("true"))
					|| (attributeValue.equalsIgnoreCase("false"))) {
				return Boolean.parseBoolean(attributeValue);
			}
			throw new JspTagException("The value of " + attributeName
					+ " must be either true or false.");
		}

		return defaultValue;
	}
}