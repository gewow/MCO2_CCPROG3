        JButton upButton = new JButton("↑");
        JButton downButton = new JButton("↓");
        JButton leftButton = new JButton("←");
        JButton rightButton = new JButton("→");

        // Sub-panel for Left and Right buttons
        JPanel lrPanel = new JPanel(); // default FlowLayout (centered)
        lrPanel.add(leftButton);
        lrPanel.add(rightButton);

        // Place buttons
        controlPanel.add(upButton, BorderLayout.NORTH);
        controlPanel.add(lrPanel, BorderLayout.CENTER);
        controlPanel.add(downButton, BorderLayout.SOUTH);

        // Add controlPanel to frame
        frame.add(controlPanel, BorderLayout.SOUTH);